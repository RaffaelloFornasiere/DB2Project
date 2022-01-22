package it.polimi.db2.telecoApp.services.impl;


import it.polimi.db2.telecoApp.dataaccess.repositories.BillingRepository;
import it.polimi.db2.telecoApp.dataaccess.repositories.OrderRepository;
import it.polimi.db2.telecoApp.services.OrderService;
import it.polimi.db2.telecoApp.services.mappers.BillingMapper;
import it.polimi.db2.telecoApp.services.mappers.OrderMapper;
import it.polimi.db2.telecoApp.services.models.Billing;
import it.polimi.db2.telecoApp.services.models.Order;
import it.polimi.db2.telecoApp.services.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BillingMapper billingMapper;
    private final BillingRepository billingRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, BillingMapper billingMapper, BillingRepository billingRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.billingMapper = billingMapper;
        this.billingRepository = billingRepository;
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
        return orderRepository.findAllByUserEntity_Username(username)
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
        Billing billing = new Billing().setOrderId(res.getId()).setResult(result);
         billingMapper.toTarget(
                billingRepository
                        .save(billingMapper.toSource(billing))

        );
         return res;
    }

    //repository declaration

    //just a wrapper method (as in the PackageServiceImpl) that is called from the resource
    //and calls the repository to retrieve all the orders from a user (remember the mapping stuff to pass from Order to OrderEntity and
    //viceversa)


}
