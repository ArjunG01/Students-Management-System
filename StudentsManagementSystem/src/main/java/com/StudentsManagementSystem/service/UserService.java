package com.StudentsManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.StudentsManagementSystem.repository.UserRepository;
import com.StudentsManagementSystem.entity.User;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public void saveUser(User user) {
        repo.save(user);
    }

    public User getByUsername(String username) {
        return repo.findByUsername(username);
    }
}
