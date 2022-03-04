package it.polimi.db2.telecoApp.web.rest;


import it.polimi.db2.telecoApp.services.OptionalPackageService;
import it.polimi.db2.telecoApp.services.models.OptionalPackage;
import it.polimi.db2.telecoApp.services.models.Package;
import it.polimi.db2.telecoApp.Utils.Pair;
import it.polimi.db2.telecoApp.services.models.TelecomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OptionalPackageResource {

    private final OptionalPackageService optionalPackageService;

    public OptionalPackageResource(OptionalPackageService optionalPackageService) {
        this.optionalPackageService = optionalPackageService;
    }

    @GetMapping("/optionalPackages/")
    ResponseEntity<List<OptionalPackage>> findAll(){
        return ResponseEntity.ok().body(
                optionalPackageService.findAll()
        );
    }

    @GetMapping("/optionalPackages/package/{id}")
    ResponseEntity<List<OptionalPackage>> getOptionalPackages(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                this.optionalPackageService.findAllByPackageId(id));
    }

    @GetMapping("/optionalPackages/average/")
    ResponseEntity<List<Pair<Package,Double>>> getAverageOptionalPackages() {
        return ResponseEntity.ok().body(
                this.optionalPackageService.findAverageOptionalPackages());
    }

    @GetMapping("/optionalPackages/best-seller")
    ResponseEntity<OptionalPackage> getBestSeller(){
        return ResponseEntity.ok().body(optionalPackageService.getBestSeller());
    }

    @PostMapping("/optionalPackages/save")
    ResponseEntity<OptionalPackage> save(@RequestBody OptionalPackage telecomService) {
        return ResponseEntity.ok().body(
                this.optionalPackageService.save(telecomService)
        );
    }
}
