package it.polimi.db2.telecoApp.web.rest;

import it.polimi.db2.telecoApp.services.PackageService;
import it.polimi.db2.telecoApp.services.models.OptionalPackage;
import it.polimi.db2.telecoApp.services.models.Package;
import it.polimi.db2.telecoApp.services.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/home/packages")
    ResponseEntity<List<Package>> findAll() {
        return ResponseEntity.ok().body(
                packageService.findAll()
        );
    }


    @PostMapping("/packages/save")
    ResponseEntity<Package> save(@RequestBody Package servicePackage) {
        return ResponseEntity.ok().body(
                this.packageService.save(servicePackage)
        );
    }


    @GetMapping("/home/packages/detail/{id}")
    ResponseEntity<Package> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                this.packageService.getDetails(id));
    }


    @GetMapping("/user-data/{username}/")
    public ResponseEntity<String> getUserData(@PathVariable String username) {
        String s = """
                  packageName: "mobile phone",
                  packageDetails: [
                    {type: "sms", amount: 10000},
                    {type: "minutes", amount: 10000},
                  ],
                  packageConsumption: [
                    {type: "sms", amount: 8900},
                    {type: "minutes", amount: 500},
                  ]
                },
                {
                  packageName: "mobile internet",
                  packageDetails: [
                    {type: "GigaBytes", amount: 100},
                  ],
                  packageConsumption: [
                    {type: "GigaBytes", amount: 12},
                  ]
                }
                              """;
        return ResponseEntity.ok().body(s);
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
