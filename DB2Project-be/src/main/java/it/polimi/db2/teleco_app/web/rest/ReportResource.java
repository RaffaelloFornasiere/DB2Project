package it.polimi.db2.teleco_app.web.rest;

import it.polimi.db2.teleco_app.utils.Pair;
import it.polimi.db2.teleco_app.services.OptionalPackageService;
import it.polimi.db2.teleco_app.services.OrderService;
import it.polimi.db2.teleco_app.services.UserService;
import it.polimi.db2.teleco_app.services.models.*;
import it.polimi.db2.teleco_app.services.models.Package;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReportResource {
    private UserService userService;
    private OrderService orderService;
    private OptionalPackageService optionalPackageService;

    @GetMapping("/report/users/insolvent")
    ResponseEntity<List<User>> getInsolventUsers(){
        return ResponseEntity.ok().body(
                this.userService.getInsolventUsers()
        );
    }

    @GetMapping("/report/alerts/")
    ResponseEntity<List<Alert>> findAllAlerts(){
        List<Alert> res = orderService.findAllAlerts();
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/report/optionalPackages/average/")
    ResponseEntity<List<Pair<Package,Double>>> getAverageOptionalPackages() {
        return ResponseEntity.ok().body(
                this.optionalPackageService.findAverageOptionalPackages());
    }

    @GetMapping("/report/optionalPackages/best-seller")
    ResponseEntity<OptionalPackage> getBestSeller(){
        return ResponseEntity.ok().body(optionalPackageService.getBestSeller());
    }
}
