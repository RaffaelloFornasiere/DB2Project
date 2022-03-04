package it.polimi.db2.teleco_app.services.impl;

import it.polimi.db2.teleco_app.dataaccess.repositories.UserRepository;
import it.polimi.db2.teleco_app.services.UserService;
import it.polimi.db2.teleco_app.services.mappers.UserMapper;
import it.polimi.db2.teleco_app.services.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAll() {
        return  userRepository.findAll().stream().map(userMapper.MAPPER::toTarget).toList();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findById(username).map(userMapper.MAPPER::toTarget);

    }

    @Override
    public Optional<User> findByNameAndSurname(String name, String surname) {
        return userRepository.findByNameAndSurname(name, surname).map(userMapper.MAPPER::toTarget);

    }

    @Override
    public User saveUser(User user) {
        return userMapper.MAPPER.toTarget(
                userRepository.save(userMapper.MAPPER.toSource(user)));
    }

    @Override
    public User editUser(User user) {
        var old = findByUsername(user.getUsername()).orElseThrow();
        //encode password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        //can't overwrite important data
        user.setInsolvent(old.getInsolvent());
        user.setRoles(old.getRoles());
        return userMapper.MAPPER.toTarget(
                userRepository.save(userMapper.MAPPER.toSource(user)));
    }


    @Override
    public List<User> getInsolventUsers() {
        return  userRepository.findAllByInsolventIsTrue()
                .stream().map(userMapper.MAPPER::toTarget).toList();
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("username " + s + " not found"));
    }




    @Override
    public void markCurrentAsInsolvent(){
        markAsInsolvent((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
    @Override
    public void markAsInsolvent(User user){
        user.setInsolvent(true);
    }
}
