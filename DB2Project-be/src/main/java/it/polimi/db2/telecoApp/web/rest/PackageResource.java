package it.polimi.db2.telecoApp.web.rest;

import it.polimi.db2.telecoApp.services.PackageService;
import it.polimi.db2.telecoApp.services.models.Package;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api")
public class PackageResource {


    private final PackageService packageService;

    public PackageResource(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/packages")
    ResponseEntity<List<Package>> findAll(){
        return ResponseEntity.ok().body(
                packageService.findAll()
        );
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }




}
