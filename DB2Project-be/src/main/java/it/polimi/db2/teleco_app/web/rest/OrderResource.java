package it.polimi.db2.teleco_app.web.rest;

import it.polimi.db2.teleco_app.services.OrderService;
import it.polimi.db2.teleco_app.services.models.Order;
import it.polimi.db2.teleco_app.services.models.Package;
import it.polimi.db2.teleco_app.utils.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderResource {
    private final OrderService orderService;


    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/orders")
    ResponseEntity<List<Order>> findAll() {
        List<Order> res = orderService.findAll();
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/orders-sorted")
    ResponseEntity<List<Order>> findAllSorted() {
        List<Order> res = orderService.findAllSorted();
        return ResponseEntity.ok().body(res);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/orders-per-day")
    ResponseEntity<List<Pair<LocalDate, Long>>> getOrdersPerDay() {
        List<Pair<LocalDate, Long>> res = orderService.findOrdersPerDate();
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/orders/rejected-orders/")
    ResponseEntity<List<Order>> findAllRejectedOrders() {
        List<Order> res = orderService.getRejectedOrders();
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/orders/suspended/")
    ResponseEntity<List<Order>> getSuspendedOrders() {
        List<Order> res = orderService.getSuspended();
        return ResponseEntity.ok().body(res);
    }


    @GetMapping("/orders/user/{username}")
    ResponseEntity<List<Order>> findAllByUser(@PathVariable String username) {
        List<Order> res = orderService.findAllByUser(username);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/orders/orderDate/{startDate}/{endDate}")
    ResponseEntity<List<Order>> findAllByOrderDate(@PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) {
        List<Order> res = orderService.findAllByOrderDate(startDate, endDate);
        return ResponseEntity.ok().body(res);
    }


    @GetMapping("/orders/packages/")
    ResponseEntity<List<Pair<Package, Integer>>> findAllByPackage() {
        var res = orderService.findAllByPackage();
        return ResponseEntity.ok().body(res);
    }

//    @GetMapping("/orders/packages/{packageId}")
//    ResponseEntity<List<Order>> findAllByPackageId(@PathVariable Long packageId){
//        List<Order> res = orderService.findAllByPackageId(packageId);
//        return ResponseEntity.ok().body(res);
//    }
//    @GetMapping("/orders/packages/{packageId}/{validityPeriod}")
//    ResponseEntity<List<Order>> findAllByPackageIdAndVP(@PathVariable Long packageId, @PathVariable ValidityPeriod validityPeriod){
//        List<Order> res = orderService.findAllByPackageIdAndVP(packageId, validityPeriod);
//        return ResponseEntity.ok().body(res);
//    }

    @PostMapping("/orders/buy/{result}")
    ResponseEntity<Pair<Order, Boolean>> buy(@PathVariable Boolean result, @RequestBody Order order) {
        return ResponseEntity.ok().body(
                this.orderService.buy(order, result));
    }

//    @PostMapping("/orders/retry-payment/{result}")
//    ResponseEntity<Pair<Order, Boolean>> retryPayment(@PathVariable Boolean result, @RequestBody Order order) {
//        return ResponseEntity.ok().body(
//                this.orderService.tryPayment(order, result));
//    }

}


