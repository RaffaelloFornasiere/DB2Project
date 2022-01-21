package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.services.models.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<Order> findAll();

    List<Order> findAllByUser(String username);

    List<Order> findAllByOrderDate(LocalDateTime start, LocalDateTime end);

    List<Order> findAllByPackageId(Long packageId);
}
