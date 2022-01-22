package it.polimi.db2.telecoApp.services.impl;

import it.polimi.db2.telecoApp.dataaccess.repositories.UserRepository;
import it.polimi.db2.telecoApp.services.OrderService;
import it.polimi.db2.telecoApp.services.UserService;
import it.polimi.db2.telecoApp.services.mappers.UserMapper;
import it.polimi.db2.telecoApp.services.models.Order;
import it.polimi.db2.telecoApp.services.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrderService orderService;

    public UserServiceImpl(UserRepository userRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @Override
    public List<User> findAll() {
        return  userRepository.findAll().stream().map(UserMapper.MAPPER::toTarget).toList();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findById(username).map(UserMapper.MAPPER::toTarget);

    }

    @Override
    public Optional<User> findByNameAndSurname(String name, String surname) {
        return userRepository.findByNameAndSurname(name, surname).map(UserMapper.MAPPER::toTarget);

    }

    @Override
    public User saveUser(User user) {
        return UserMapper.MAPPER.toTarget(
                userRepository.save(UserMapper.MAPPER.toSource(user)));
    }



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("username " + s + " not found"));
    }


    @Override
    public List<User> getInsolventUsers() {
        var rejectedOrders = orderService.getRejectedOrders()
                .stream().map(Order::getUser).distinct().toList();
        return rejectedOrders;
    }
}
