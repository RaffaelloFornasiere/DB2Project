package it.polimi.db2.telecoApp.web.rest;


import it.polimi.db2.telecoApp.services.OptionalPackageService;
import it.polimi.db2.telecoApp.services.models.OptionalPackage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OptionalPackageResource {

    private final OptionalPackageService optionalPackageService;

    public OptionalPackageResource(OptionalPackageService optionalPackageService) {
        this.optionalPackageService = optionalPackageService;
    }

    @GetMapping("/optionalPackages")
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
}