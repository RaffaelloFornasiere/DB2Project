package it.polimi.db2.telecoApp.security;

import it.polimi.db2.telecoApp.dataaccess.repositories.RoleRepository;
import it.polimi.db2.telecoApp.security.payload.JwtResponse;
import it.polimi.db2.telecoApp.security.payload.LoginRequest;
import it.polimi.db2.telecoApp.services.UserService;
import it.polimi.db2.telecoApp.services.enums.Role;
import it.polimi.db2.telecoApp.services.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;


    public AuthController(AuthenticationManager authenticationManager, UserService userService, RoleRepository roleRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, user));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) throws RoleNotFoundException {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Username is already taken");
        }
        if(user.getRoles() == null || user.getRoles().isEmpty())
            user.setRoles(Set.of(Role.ROLE_USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().stream()
                .filter(r -> roleRepository.findByRole(r).isPresent()).findAny()
                .orElseThrow(() -> new RoleNotFoundException("Error: role is not found"));


        userService.saveUser(user);
        return ResponseEntity.ok("{ \"message\": \"User registered successful\" }");
    }
}
