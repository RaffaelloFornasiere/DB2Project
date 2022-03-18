package it.polimi.db2.teleco_app.services.impl;


import it.polimi.db2.teleco_app.dataaccess.entities.OrderEntity;
import it.polimi.db2.teleco_app.dataaccess.repositories.AlertRepository;
import it.polimi.db2.teleco_app.dataaccess.repositories.BillingRepository;
import it.polimi.db2.teleco_app.dataaccess.repositories.OrderRepository;
import it.polimi.db2.teleco_app.services.OrderService;
import it.polimi.db2.teleco_app.services.mappers.AlertMapper;
import it.polimi.db2.teleco_app.services.mappers.BillingMapper;
import it.polimi.db2.teleco_app.services.mappers.OrderMapper;
import it.polimi.db2.teleco_app.services.models.Package;
import it.polimi.db2.teleco_app.services.models.*;
import it.polimi.db2.teleco_app.utils.Pair;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BillingMapper billingMapper;
    private final BillingRepository billingRepository;

    private final AlertRepository alertRepository;
    private final AlertMapper alertMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, BillingMapper billingMapper, BillingRepository billingRepository, AlertRepository alertRepository, AlertMapper alertMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.billingMapper = billingMapper;
        this.billingRepository = billingRepository;
        this.alertRepository = alertRepository;
        this.alertMapper = alertMapper;
    }


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toTarget)
                .toList();
    }

    @Override
    public List<Billing> findAllBillingsByOrderId(Long orderId) {
        return billingRepository.findAllByOrderIdNative(orderId).stream().map(billingMapper::toTarget).toList();
    }


    @Override
    public List<Order> findAllByUser(String username) {
        return orderRepository.findAllByUser_UsernameOrderByOrderDate(username)
                .stream()
                .map(orderMapper::toTarget)
                .toList();
    }

    @Override
    public List<Pair<LocalDate, Long>> findOrdersPerDate() {
        return orderRepository.getOrdersPerDay()
                .stream().map(i -> new Pair<>(((java.sql.Date) i[0]).toLocalDate(), ((BigInteger) i[1]).longValue())).toList();
    }


    @Override
    public List<Order> findAllByOrderDate(LocalDateTime start, LocalDateTime end) {
        return orderRepository.findAllByOrderDateAfterAndOrderDateBeforeJPQL(start, end)
                .stream()
                .map(orderMapper::toTarget)
                .toList();
    }

//    @Override
//    public List<Order> findAllByPackageId(Long packageId) {
//        return orderRepository.findAllByPackageIdNative(packageId)
//                .stream()
//                .map(orderMapper::toTarget)
//                .toList();
//    }


    @Override
    public Pair<Order, Boolean> buy(Order order, Boolean result) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUser(user.getUsername());

        var entity = orderMapper.toSource(order).setSuspended(!result);
        Order res = orderMapper.toTarget(
                orderRepository
                        .save(entity));
        return tryPayment(res, result);
    }


    @Override
    public Pair<Order, Boolean> tryPayment(Order order, Boolean result) {
        order.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        Billing billing = new Billing()
                .setOrderId(order.getId()).setResult(result)
                .setBillingDateTime(LocalDateTime.now());
        billingMapper.toTarget(
                billingRepository
                        .save(billingMapper.toSource(billing))
        );

        return new Pair<>(order, result);
    }


    @Override
    public List<Order> getRejectedOrders() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderRepository.findAllByUser_UsernameOrderByOrderDate(user.getUsername())
                .stream()
                .filter(OrderEntity::getSuspended)
                .map(orderMapper::toTarget)
                .toList();
    }

    @Override
    public List<Order> getSuspended() {
        return orderRepository.findAll()
                .stream()
                .filter(OrderEntity::getSuspended)
                .map(orderMapper::toTarget)
                .toList();
    }

    @Override
    public List<Alert> findAllAlerts() {
        return alertRepository.findAll().stream().map(alertMapper::toTarget).toList();
    }


    @Override
    public List<Pair<Package, Integer>> findAllByPackage() {
        var aux = orderRepository.findAll()
                .stream().map(orderMapper::toTarget).toList();
        return aux.stream()
                .map(i -> new Pair<Package, Integer>(i.getServicePackage(), 1))
                .distinct()
                .peek(i ->
                        i.setSecond((int) aux.stream()
                                .filter(order -> order.getServicePackage().getId().equals(i.getFirst().getId()))
                                .count()
                        )
                ).toList();
    }

    @Override
    public List<Order> findAllSorted() {
        return orderRepository.findAllByOrderByOrderDate()
                .stream()
                .map(orderMapper::toTarget)
                .toList();
    }

    @Override
    public List<Order> findAllActiveByUser(String username) {
        return orderRepository.findAllActiveByUser(username)
                .stream().map(orderMapper::toTarget).toList();
    }


}
