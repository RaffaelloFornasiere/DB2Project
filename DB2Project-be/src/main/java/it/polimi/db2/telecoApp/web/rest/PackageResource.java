package it.polimi.db2.telecoApp.web.rest;

import it.polimi.db2.telecoApp.services.PackageService;
import it.polimi.db2.telecoApp.services.models.Package;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
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
