package it.polimi.db2.telecoApp.web.rest;

import it.polimi.db2.telecoApp.services.UserService;
import it.polimi.db2.telecoApp.services.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/insolvent")
    ResponseEntity<List<User>> getInsolventUsers(){
        return ResponseEntity.ok().body(
                this.userService.getInsolventUsers()
        );
    }

}
