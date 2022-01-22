package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.services.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    Optional<User> findByUsername(String username);
    Optional<User> findByNameAndSurname(String name, String surname);
    User saveUser(User user);

    List<User> getInsolventUsers();

    void markCurrentAsInsolvent();

    void markAsInsolvent(User user);
}
