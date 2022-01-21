package it.polimi.db2.telecoApp.web.rest;

import it.polimi.db2.telecoApp.services.ServiceService;
import it.polimi.db2.telecoApp.services.models.TelecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceResource {

    private final ServiceService serviceService;

    public ServiceResource(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/services")
    ResponseEntity<List<TelecoService>> findAll() {
        return ResponseEntity.ok().body(
                serviceService.findAll()
        );
    }

    @PostMapping("/services/save")
    ResponseEntity<TelecoService> save(@RequestBody TelecoService telecoService) {
        return ResponseEntity.ok().body(
                this.serviceService.save(telecoService)
        );
    }
}
