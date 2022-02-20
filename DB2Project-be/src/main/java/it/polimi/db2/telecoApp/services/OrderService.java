package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.services.models.Alert;
import it.polimi.db2.telecoApp.services.models.Order;
import it.polimi.db2.telecoApp.services.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<Order> findAll();

    List<Order> findAllByUser(String username);

    List<Order> findAllByOrderDate(LocalDateTime start, LocalDateTime end);

    List<Order> findAllByPackageId(Long packageId);

    Order save(Order order, Boolean result) throws Exception;


    Boolean tryPayment(Order order, Boolean result) throws Exception;

    List<Order> getRejectedOrders();

    List<Order> getSuspended();

    List<Alert> findAllAlerts();

    List<User> getInsolventUsers();
}
