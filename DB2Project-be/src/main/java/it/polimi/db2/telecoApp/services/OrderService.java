package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.Utils.Pair;
import it.polimi.db2.telecoApp.services.models.*;
import it.polimi.db2.telecoApp.services.models.Package;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> findAll();

    List<Order> findAllByUser(String username);

    List<Order> findAllByOrderDate(LocalDateTime start, LocalDateTime end);

    List<Order> findAllByPackageId(Long packageId);
    List<Order> findAllByPackageIdAndVP(Long packageId, ValidityPeriod validityPeriod);
    Map<Pair<Package, ValidityPeriod>, Integer> findAllByPackageAndVP();


    Pair<Order, Boolean> buy(Order order, Boolean result) throws Exception;


    Boolean tryPayment(Order order, Boolean result) throws Exception;

    List<Order> getRejectedOrders();

    List<Order> getSuspended();

    List<Alert> findAllAlerts();

    List<User> getInsolventUsers();

    List<Pair<Package, Integer>> findAllByPackage();

}
