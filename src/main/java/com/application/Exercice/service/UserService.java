package com.application.Exercice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.application.Exercice.model.Role;
import com.application.Exercice.model.User;

@Service
public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();

}
