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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReportResource {
    private final UserService userService;
    private final OrderService orderService;
    private final OptionalPackageService optionalPackageService;
    private final ReportRepository reportRepository;

    public ReportResource(UserService userService, OrderService orderService, OptionalPackageService optionalPackageService, ReportRepository reportRepository) {
        this.userService = userService;
        this.orderService = orderService;
        this.optionalPackageService = optionalPackageService;
        this.reportRepository = reportRepository;
    }

    @GetMapping("/report/users/insolvent")
    ResponseEntity<List<User>> getInsolventUsers() {
        return ResponseEntity.ok().body(
                this.userService.getInsolventUsers()
        );
    }

    @GetMapping("/report/alerts/")
    ResponseEntity<List<Alert>> findAllAlerts() {
        List<Alert> res = orderService.findAllAlerts();
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/report/optionalPackages/average/")
    ResponseEntity<List<SalesPackageReportEntity>> getAverageOptionalPackages() {
        return ResponseEntity.ok().body(
                this.reportRepository.findAllPackageReport());
    }

    @GetMapping("/report/optionalPackages/best-seller")
    ResponseEntity<List<SalesOptionalReportEntity>> getBestSeller() {
        return ResponseEntity.ok().body(reportRepository.findAllOptionalReport());
    }


    @GetMapping("/report/packages-and-vp/")
    ResponseEntity<List<SalesValidityReportEntity>> findAllByPackageAndVP() {
        var res = reportRepository.findAllValidityReport();
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/report/users-report")
    ResponseEntity<List<UserReport>> getUsersReport(){
        var orders = orderService.getSuspended();
        var alerts = orderService.findAllAlerts();
        var users = orders.stream().map(Order::getUser).distinct();
        var res = users.map(u -> new UserReport(u,
                orders.stream().filter(o -> o.getUser().equals(u)).map(
                        o -> new Pair<>(o, orderService.findAllBillingsByOrderId(o.getId()))
                ).toList(),
                alerts.stream().filter(alert -> alert.getUsername().equals(u.getUsername())).findAny().orElse(null))
        ).toList();
        return ResponseEntity.ok().body(res);
    }

}
