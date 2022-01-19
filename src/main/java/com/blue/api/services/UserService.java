package com.blue.api.services;

import com.blue.api.entities.User;
import com.blue.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userDetailsRepository;

    @Autowired
    public UserService(UserRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = this.userDetailsRepository.findByEmail(email);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.get();
    }
}
