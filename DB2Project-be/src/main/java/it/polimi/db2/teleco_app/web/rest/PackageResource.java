package it.polimi.db2.teleco_app.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.db2.teleco_app.services.PackageService;
import it.polimi.db2.teleco_app.services.ValidityPeriodService;
import it.polimi.db2.teleco_app.services.models.OptionalPackage;
import it.polimi.db2.teleco_app.services.models.Package;
import it.polimi.db2.teleco_app.services.models.ValidityPeriod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    ResponseEntity<List<Package>> findAll() throws JsonProcessingException {
        return ResponseEntity.ok().body(
                packageService.findAll()
        );
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/packages/delete/{packageId}")
    ResponseEntity<Void> deletePackage(@PathVariable Long packageId){
        this.packageService.delete(packageId);
        return ResponseEntity.ok().build();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/packages/save/")
    ResponseEntity<Package> save(@RequestBody List<String> wrapper) throws JsonProcessingException {
        Package servicePackage = new ObjectMapper().readValue(wrapper.get(0), Package.class);
        List<OptionalPackage> optionalPackages = new ObjectMapper().readValue(wrapper.get(1), new TypeReference<List<OptionalPackage>>(){});
        List<ValidityPeriod> validityPeriods = new ObjectMapper().readValue(wrapper.get(2), new TypeReference<List<ValidityPeriod>>(){});

        return ResponseEntity.ok().body(
                this.packageService.saveWithDetails(servicePackage, optionalPackages, validityPeriods)
        );
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/validityPeriod/save/")
    ResponseEntity<ValidityPeriod> saveValidityPeriod(@RequestBody ValidityPeriod validityPeriod){
        return ResponseEntity.ok().body(
                this.validityPeriodService.save(validityPeriod)
        );
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/validityPeriod/delete/{validityPeriodId}")
    ResponseEntity<Void> deleteValidityPeriod(@PathVariable Long validityPeriodId){
        this.validityPeriodService.delete(validityPeriodId);
        return ResponseEntity.ok().body(null);

    }

    @GetMapping("/home/packages/detail/{id}")
    ResponseEntity<Package> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                this.packageService.getDetails(id));
    }

    @GetMapping("/home/packages/validity-periods/{packageId}")
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

}
