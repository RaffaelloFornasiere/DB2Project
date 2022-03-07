package it.polimi.db2.teleco_app.services.impl;


import it.polimi.db2.teleco_app.utils.Pair;
import it.polimi.db2.teleco_app.dataaccess.repositories.AlertRepository;
import it.polimi.db2.teleco_app.dataaccess.repositories.BillingRepository;
import it.polimi.db2.teleco_app.dataaccess.repositories.OrderRepository;
import it.polimi.db2.teleco_app.services.OrderService;
import it.polimi.db2.teleco_app.services.mappers.AlertMapper;
import it.polimi.db2.teleco_app.services.mappers.BillingMapper;
import it.polimi.db2.teleco_app.services.mappers.OrderMapper;
import it.polimi.db2.teleco_app.services.models.*;
import it.polimi.db2.teleco_app.services.models.Package;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<Order> findAllByUser(String username) {
        return orderRepository.findAllByUser_Username(username)
                .stream()
                .map(orderMapper::toTarget)
                .toList();
    }

    @Override
    public List<Pair<LocalDate, Long>> findOrdersPerDate() {
        return orderRepository.getOrdersPerDay()
                .stream().map(i -> new Pair<>(((java.sql.Date)i[0]).toLocalDate(), ((BigInteger)i[1]).longValue())).toList();
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
    public List<Order> findAllByPackageIdAndVP(Long packageId, ValidityPeriod validityPeriod) {
        return null;
    }

    @Override
    public Pair<Order, Boolean> buy(Order order, Boolean result) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUser(user);
        Order res = orderMapper.toTarget(
                orderRepository
                        .save(orderMapper.toSource(order)));
        return tryPayment(res, result);
    }


    @Override
    public Pair<Order, Boolean> tryPayment(Order order, Boolean result) {
        order.setUser( (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
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
        List<Order> orders = orderRepository.findAllByUser_Username(user.getUsername())
                .stream()
                .map(orderMapper::toTarget)
                .toList();
        return extractRejected(orders);
    }

    @Override
    public List<Order> getSuspended() {
        List<Order> orders = orderRepository.findAll()
                .stream()
                .map(orderMapper::toTarget)
                .toList();
        return extractRejected(orders);
    }

    @Override
    public List<Alert> findAllAlerts() {
        return alertRepository.findAll().stream().map(alertMapper::toTarget).toList();
    }

    @Override
    public List<User> getInsolventUsers() {
        return getRejectedOrders()
                .stream().map(Order::getUser).distinct().toList();
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
    public Map<Pair<Package, ValidityPeriod>, Integer> findAllByPackageAndVP() {
        var aux = orderRepository.findAll()
                .stream().map(orderMapper::toTarget).toList();
        return aux.stream()
                .map(i -> Map.entry(new Pair<>(i.getServicePackage(), i.getValidityPeriod()), 1))
                .distinct()
                .map(i ->
                        Map.entry(i.getKey(), (int) aux.stream()
                                .filter(order ->
                                        order.getServicePackage().getId().equals(i.getKey().getFirst().getId()) &&
                                                order.getValidityPeriod().getId().equals(i.getKey().getSecond().getId())
                                )
                                .count())
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Order> extractRejected(List<Order> orders) {
        List<Order> res = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            Billing lastBilling = billingMapper.toTarget(billingRepository
                    .findOneByOrderIdNative(orders.get(i).getId()));
            if ( lastBilling != null && lastBilling.getResult().equals(false))
                res.add(orders.get(i));
        }
        return res;
    }


}
