package it.polimi.db2.telecoApp.services.impl;


import it.polimi.db2.telecoApp.dataaccess.repositories.BillingRepository;
import it.polimi.db2.telecoApp.dataaccess.repositories.OrderRepository;
import it.polimi.db2.telecoApp.services.OrderService;
import it.polimi.db2.telecoApp.services.UserService;
import it.polimi.db2.telecoApp.services.mappers.BillingMapper;
import it.polimi.db2.telecoApp.services.mappers.OrderMapper;
import it.polimi.db2.telecoApp.services.models.Billing;
import it.polimi.db2.telecoApp.services.models.Order;
import it.polimi.db2.telecoApp.services.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BillingMapper billingMapper;
    private final BillingRepository billingRepository;
    private final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, BillingMapper billingMapper, BillingRepository billingRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.billingMapper = billingMapper;
        this.billingRepository = billingRepository;
        this.userService = userService;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toTarget)
                .toList();
    }


    @Override
    public List<Order> findAllByUser(String username) {
        return orderRepository.findAllByUser_Username(username)
                .stream()
                .map(orderMapper::toTarget)
                .toList();
    }

    @Override
    public List<Order> findAllByOrderDate(LocalDateTime start, LocalDateTime end) {
        return orderRepository.findAllByOrderDateAfterAndOrderDateBeforeJPQL(start, end)
                .stream()
                .map(orderMapper::toTarget)
                .toList();
    }

    @Override
    public List<Order> findAllByPackageId(Long packageId) {
        return orderRepository.findAllByPackageIdNative(packageId)
                .stream()
                .map(orderMapper::toTarget)
                .toList();
    }

    @Override
    public Order save(Order order, Boolean result) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUser(user);
        Order res = orderMapper.toTarget(
                orderRepository
                        .save(orderMapper.toSource(order)));
        tryPayment(order, result);
        return res;
    }

    @Override
    public Order tryPayment(Order order, Boolean result) throws Exception {
        Billing billing = new Billing().setOrderId(order.getId()).setResult(result);
        billingMapper.toTarget(
                billingRepository
                        .save(billingMapper.toSource(billing))
        );
        if (getRejectedOrders().size() > 3)
            userService.markCurrentAsInsolvent();
        return order;
    }


    @Override
    public List<Order> getRejectedOrders() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orders = orderRepository.findAllByUser_Username(user.getUsername())
                .stream()
                .map(orderMapper::toTarget)
                .toList();
        return extractRejected(orders);
    }

    @Override
    public List<Order> getSuspended()  {
        List<Order> orders = orderRepository.findAll()
                .stream()
                .map(orderMapper::toTarget)
                .toList();
        return extractRejected(orders);
    }

    private List<Order> extractRejected(List<Order> orders){
        List<Order> res = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            Billing lastBilling = billingMapper.toTarget(billingRepository
                    .findOneByOrderIdNative(orders.get(i).getId()));
            if (lastBilling.getResult().equals(false))
                res.add(orders.get(i));
        }
        return res;
    }


}
