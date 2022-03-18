package it.polimi.db2.teleco_app.services;

import it.polimi.db2.teleco_app.services.models.Alert;
import it.polimi.db2.teleco_app.services.models.Billing;
import it.polimi.db2.teleco_app.services.models.Order;
import it.polimi.db2.teleco_app.services.models.Package;
import it.polimi.db2.teleco_app.utils.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<Order> findAll();

    List<Billing> findAllBillingsByOrderId(Long orderId);

    List<Order> findAllByUser(String username);

    List<Pair<LocalDate, Long>> findOrdersPerDate();

    List<Order> findAllByOrderDate(LocalDateTime start, LocalDateTime end);

//    List<Order> findAllByPackageId(Long packageId);

    Pair<Order, Boolean> buy(Order order, Boolean result);


    Pair<Order, Boolean> tryPayment(Order order, Boolean result);

    List<Order> getRejectedOrders();

    List<Order> getSuspended();

    List<Alert> findAllAlerts();

    List<Pair<Package, Integer>> findAllByPackage();

    List<Order> findAllSorted();

    List<Order> findAllActiveByUser(String username);
}
