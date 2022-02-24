package it.polimi.db2.telecoApp.web.rest;

import it.polimi.db2.telecoApp.services.OrderService;
import it.polimi.db2.telecoApp.services.models.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderResource {
    //implement a rest method that retrieves all the orders from a username(String)

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/orders")
    ResponseEntity<List<Order>> findAll(){
        List<Order> res = orderService.findAll();
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/orders/user/{username}")
    ResponseEntity<List<Order>> findAllByUser(@PathVariable String username){
        List<Order> res = orderService.findAllByUser(username);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/orders/orderDate/{startDate}/{endDate}")
    ResponseEntity<List<Order>> findAllByOrderDate(@PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate){
        List<Order> res = orderService.findAllByOrderDate(startDate, endDate);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/orders/packages/{packageId}")
    ResponseEntity<List<Order>> findAllByPackageId(@PathVariable Long packageId){
        List<Order> res = orderService.findAllByPackageId(packageId);
        return ResponseEntity.ok().body(res);
    }
    @GetMapping("/orders/packages/{packageId}/{validityPeriod}")
    ResponseEntity<List<Order>> findAllByPackageIdAndVP(@PathVariable Long packageId, @PathVariable ValidityPeriod validityPeriod){
        List<Order> res = orderService.findAllByPackageIdAndVP(packageId, validityPeriod);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/orders/save/{result}")
    ResponseEntity<Order> save(@PathVariable Boolean result, @RequestBody Order order) throws Exception {
        return ResponseEntity.ok().body(
                this.orderService.save(order, result));
    }

    @PostMapping("/orders/retry-payment/{result}")
    ResponseEntity<Order> retryPayment(@PathVariable Boolean result, @RequestBody Order order) throws Exception {
        return ResponseEntity.ok().body(
                this.orderService.save(order, result));
    }

    @GetMapping("/orders/alerts/}")
    ResponseEntity<List<Alert>> findAllAlerts(){
        List<Alert> res = orderService.findAllAlerts();
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/orders/users/insolvent")
    ResponseEntity<List<User>> getInsolventUsers(){
        return ResponseEntity.ok().body(
                this.orderService.getInsolventUsers()
        );
    }

    @GetMapping("/orders/rejectedOrders")
    ResponseEntity<List<Order>> getRejectedOrders(){
        List<Order> res = orderService.getRejectedOrders();
        return ResponseEntity.ok().body(res);
    }
}


