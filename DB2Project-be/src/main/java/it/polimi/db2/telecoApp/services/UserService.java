package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.services.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findByUsername(String username);
    Optional<User> findByNameAndSurname(String name, String surname);

    User saveUser(User user);
}
