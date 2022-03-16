package it.polimi.db2.teleco_app.web.rest;

import it.polimi.db2.teleco_app.dataaccess.entities.SalesOptionalReportEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.SalesPackageReportEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.SalesValidityReportEntity;
import it.polimi.db2.teleco_app.dataaccess.repositories.ReportRepository;
import it.polimi.db2.teleco_app.utils.Pair;
import it.polimi.db2.teleco_app.services.OptionalPackageService;
import it.polimi.db2.teleco_app.services.OrderService;
import it.polimi.db2.teleco_app.services.UserService;
import it.polimi.db2.teleco_app.services.models.*;
import it.polimi.db2.teleco_app.services.models.Package;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReportResource {
    private final UserService userService;
    private final OrderService orderService;
    private final ReportRepository reportRepository;

    public ReportResource(UserService userService, OrderService orderService, ReportRepository reportRepository) {
        this.userService = userService;
        this.orderService = orderService;
        this.reportRepository = reportRepository;
    }



    @Secured("ROLE_ADMIN")
    @GetMapping("/report/users/insolvent")
    ResponseEntity<List<User>> getInsolventUsers() {
        return ResponseEntity.ok().body(
                this.userService.getInsolventUsers()
        );
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/report/alerts/")
    ResponseEntity<List<Alert>> findAllAlerts() {
        List<Alert> res = orderService.findAllAlerts();
        return ResponseEntity.ok().body(res);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/report/optionalPackages/average/")
    ResponseEntity<List<SalesPackageReportEntity>> getAverageOptionalPackages() {
        return ResponseEntity.ok().body(
                this.reportRepository.findAllPackageReport());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/report/optionalPackages/best-seller")
    ResponseEntity<List<SalesOptionalReportEntity>> getBestSeller() {
        return ResponseEntity.ok().body(reportRepository.findAllOptionalReport());
    }


    @Secured("ROLE_ADMIN")
    @GetMapping("/report/packages-and-vp/")
    ResponseEntity<List<SalesValidityReportEntity>> findAllByPackageAndVP() {
        var res = reportRepository.findAllValidityReport();
        return ResponseEntity.ok().body(res);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/report/users-report")
    ResponseEntity<List<UserReport>> getUsersReport(){
        var orders = orderService.getSuspended();
        var alerts = orderService.findAllAlerts();
        var users = orders.stream().map(Order::getUser).distinct();
        var res = users.map(u -> new UserReport(userService.findByUsername(u).orElseThrow(),
                orders.stream().filter(o -> o.getUser().equals(u)).map(
                        o -> new Pair<>(o, orderService.findAllBillingsByOrderId(o.getId()))
                ).toList(),
                alerts.stream().filter(alert -> alert.getUsername().equals(u)).findAny().orElse(null))
        ).toList();
        return ResponseEntity.ok().body(res);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/report/user/active-packages/{username}")
    ResponseEntity<List<Order>> findActiveOrders(@PathVariable String username){
        return ResponseEntity.ok().body(
                orderService.findAllActiveByUser(username));
    }

    @GetMapping("/report/user-cumulative-services/{username}")
    ResponseEntity<Map<String, Object>> getUserCumulativeServices(@PathVariable String username){
        return ResponseEntity.ok().body(
                reportRepository.getUserCumulativeServices(username));

    }

}
