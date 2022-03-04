package it.polimi.db2.teleco_app.web.rest;


import it.polimi.db2.teleco_app.services.OptionalPackageService;
import it.polimi.db2.teleco_app.services.models.OptionalPackage;
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



    @PostMapping("/optionalPackages/save")
    ResponseEntity<OptionalPackage> save(@RequestBody OptionalPackage telecomService) {
        return ResponseEntity.ok().body(
                this.optionalPackageService.save(telecomService)
        );
    }
}
