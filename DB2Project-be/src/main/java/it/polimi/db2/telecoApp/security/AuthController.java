package it.polimi.db2.telecoApp.security;

import it.polimi.db2.telecoApp.dataaccess.repositories.RoleRepository;
import it.polimi.db2.telecoApp.security.payload.JwtResponse;
import it.polimi.db2.telecoApp.security.payload.LoginRequest;
import it.polimi.db2.telecoApp.security.payload.SignupRequest;
import it.polimi.db2.telecoApp.services.UserService;
import it.polimi.db2.telecoApp.services.enums.Role;
import it.polimi.db2.telecoApp.services.models.User;
import it.polimi.db2.telecoApp.services.models.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(JwtResponse
                .builder()
                .token(jwt)
                .username(userDetails.getUsername())
                .roles(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .build());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) throws RoleNotFoundException {
        if (userService.findByUsername(signupRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Username is already taken");
        }

        User user = User.builder()
                .username(signupRequest.getUsername())
                .name(signupRequest.getName())
                .surname(signupRequest.getSurname())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build();

        Set<String> strRoles = Objects.requireNonNullElse(signupRequest.getRole(), Set.of(""));
        Set<Role> roles = new HashSet<>();

        for (String role : strRoles) {
            String match = "role_" + role;
            match = match.toUpperCase();
            Role certifiedRole = roleRepository.findByRole(Role.valueOf(match))
                    .orElseThrow(() -> new RoleNotFoundException("Error: role is not found"))
                    .getRole();
            roles.add(certifiedRole);
        }

        user.setRoles(roles);
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
