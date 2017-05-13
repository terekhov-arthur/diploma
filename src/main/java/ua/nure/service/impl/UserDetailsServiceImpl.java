package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.nure.model.User;
import ua.nure.model.security.UserDetailsImpl;
import ua.nure.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        throwIfNull(username, user);
        return new UserDetailsImpl(user);
    }

    private void throwIfNull(String username, User user) {
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
    }
}
