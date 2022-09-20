package com.application.Exercice.jwt;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.Exercice.model.User;
import com.application.Exercice.repository.UserRepository;

// JwtUserDetailsService is used to load user-specific data in the security framework.
// It queries the database for the user based on the username.
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    // loadUserByUsername allows to get the user from the database by his username
    // if the user doesn't exist, it throws an exception
    // if the user exists, it returns the user
    // the user is used to generate the token
    // the token is sent to the client
    // the client sends the token in the Authorization header of the request
    // the token is validated by the JwtFilter
    // if the token is valid, the request is executed
    // if the token is invalid, the request is not executed and a 401 unauthorized error is returned

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}