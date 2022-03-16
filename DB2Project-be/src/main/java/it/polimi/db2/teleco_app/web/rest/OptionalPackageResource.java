package it.polimi.db2.teleco_app.web.rest;


import it.polimi.db2.teleco_app.services.OptionalPackageService;
import it.polimi.db2.teleco_app.services.models.OptionalPackage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
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

    @GetMapping("/home/optionalPackages/package/{id}")
    ResponseEntity<List<OptionalPackage>> getOptionalPackages(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                this.optionalPackageService.findAllByPackageId(id));
    }



    @Secured("ROLE_ADMIN")
    @PostMapping("/optionalPackages/save")
    ResponseEntity<OptionalPackage> save(@RequestBody OptionalPackage optionalPackage) {
        return ResponseEntity.ok().body(
                this.optionalPackageService.save(optionalPackage)
        );
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/optionalPackages/delete/{optionalPackageId}")
    ResponseEntity<Void> delete(@PathVariable Long optionalPackageId){
        optionalPackageService.delete(optionalPackageId);
        return ResponseEntity.ok().body(null);
    }
}
