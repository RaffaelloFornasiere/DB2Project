package it.polimi.db2.teleco_app.web.rest;

import it.polimi.db2.teleco_app.services.ServiceService;
import it.polimi.db2.teleco_app.services.models.TelecomService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ServiceResource {

    private final ServiceService serviceService;

    public ServiceResource(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/services")
    ResponseEntity<List<TelecomService>> findAll() {
        return ResponseEntity.ok().body(
                serviceService.findAll()
        );
    }

    @GetMapping("/services/{serviceId}")
    ResponseEntity<TelecomService> findById(@PathVariable Long serviceId) {
        return ResponseEntity.ok().body(
                serviceService.findById(serviceId)
        );
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/services/save/")
    ResponseEntity<TelecomService> save(@RequestBody TelecomService telecomService) {
        return ResponseEntity.ok().body(
                this.serviceService.save(telecomService)
        );
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/services/delete/{serviceId}")
    ResponseEntity<Void> delete(@PathVariable Long serviceId){
        serviceService.delete(serviceId);
        return ResponseEntity.ok().body(null);
    }
}
