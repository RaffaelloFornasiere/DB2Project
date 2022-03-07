package it.polimi.db2.teleco_app.services;

import it.polimi.db2.teleco_app.utils.Pair;
import it.polimi.db2.teleco_app.services.models.*;
import it.polimi.db2.teleco_app.services.models.Package;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> findAll();

    List<Order> findAllByUser(String username);

    List<Pair<LocalDate, Long>> findOrdersPerDate();

    List<Order> findAllByOrderDate(LocalDateTime start, LocalDateTime end);

    List<Order> findAllByPackageId(Long packageId);
    List<Order> findAllByPackageIdAndVP(Long packageId, ValidityPeriod validityPeriod);
    Map<Pair<Package, ValidityPeriod>, Integer> findAllByPackageAndVP();


    Pair<Order, Boolean> buy(Order order, Boolean result);


    Pair<Order, Boolean> tryPayment(Order order, Boolean result);

    List<Order> getRejectedOrders();

    List<Order> getSuspended();

    List<Alert> findAllAlerts();

    List<User> getInsolventUsers();

    List<Pair<Package, Integer>> findAllByPackage();

    List<Order> findAllSorted();
}
