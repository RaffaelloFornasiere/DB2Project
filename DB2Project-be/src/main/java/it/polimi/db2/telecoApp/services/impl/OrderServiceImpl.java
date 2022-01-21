package it.polimi.db2.telecoApp.services.impl;


import it.polimi.db2.telecoApp.dataaccess.repositories.OrderRepository;
import it.polimi.db2.telecoApp.services.OrderService;
import it.polimi.db2.telecoApp.services.mappers.OrderMapper;
import it.polimi.db2.telecoApp.services.models.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
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

    //repository declaration

    //just a wrapper method (as in the PackageServiceImpl) that is called from the resource
    //and calls the repository to retrieve all the orders from a user (remember the mapping stuff to pass from Order to OrderEntity and
    //viceversa)


}
