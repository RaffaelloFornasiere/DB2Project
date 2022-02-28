package it.polimi.db2.telecoApp.web.rest;

import it.polimi.db2.telecoApp.services.PackageService;
import it.polimi.db2.telecoApp.services.ValidityPeriodService;
import it.polimi.db2.telecoApp.services.models.OptionalPackage;
import it.polimi.db2.telecoApp.services.models.Package;
import it.polimi.db2.telecoApp.services.models.ValidityPeriod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PackageResource {

    private final ValidityPeriodService validityPeriodService;
    private final PackageService packageService;

    public PackageResource(ValidityPeriodService validityPeriodService, PackageService packageService) {
        this.validityPeriodService = validityPeriodService;
        this.packageService = packageService;
    }

    @GetMapping("/home/packages")
    ResponseEntity<List<Package>> findAll() {
        return ResponseEntity.ok().body(
                packageService.findAll()
        );
    }


    @PostMapping("/packages/save/")
    ResponseEntity<Package> save(@RequestParam(name = "package") Package servicePackage,
                                 @RequestParam(name = "optionalPackages", required = false) List<OptionalPackage> optionalPackages,
                                 @RequestParam(name = "validityPeriods", required = false) List<ValidityPeriod> validityPeriods) {
        return ResponseEntity.ok().body(
                this.packageService.save(servicePackage)
        );
    }


    @GetMapping("/home/packages/detail/{id}")
    ResponseEntity<Package> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                this.packageService.getDetails(id));
    }

    @GetMapping("/packages/validity-periods/{packageId}")
    ResponseEntity<List<ValidityPeriod>> findValidityPeriodsByPackageId(@PathVariable Long packageId){
        return ResponseEntity.ok().body(
                this.validityPeriodService.findAllByPackageId(packageId)
        );
    }
    @GetMapping("/packages/validity-periods/")
    ResponseEntity<List<ValidityPeriod>> findValidityPeriods(){
        return ResponseEntity.ok().body(
                this.validityPeriodService.findAll()
        );
    }


    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }


    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

}
