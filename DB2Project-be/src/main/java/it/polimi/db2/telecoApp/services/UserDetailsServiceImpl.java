package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.dataaccess.repositories.UserRepository;
import it.polimi.db2.telecoApp.services.mappers.UserMapper;
import it.polimi.db2.telecoApp.services.models.User;
import it.polimi.db2.telecoApp.services.models.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findById(s).map(UserMapper.MAPPER::toTarget).orElseThrow();
        return UserDetailsImpl.buildFromUser(user);
    }
}
