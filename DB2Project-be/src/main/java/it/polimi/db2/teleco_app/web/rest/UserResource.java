package it.polimi.db2.teleco_app.web.rest;

import it.polimi.db2.teleco_app.services.UserService;
import it.polimi.db2.teleco_app.services.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/users/edit")
    ResponseEntity<User> editUser(@RequestBody User user){
        return ResponseEntity.ok().body(
                this.userService.editUser(user)
        );
    }

    @GetMapping("/home/users/check-username/{username}")
    ResponseEntity<Boolean> checkUsername(@PathVariable String username){
        return ResponseEntity.ok().body(
                userService.findByUsername(username).isPresent()
        );
    }


}
