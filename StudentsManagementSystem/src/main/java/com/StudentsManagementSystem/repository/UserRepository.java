package com.StudentsManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.StudentsManagementSystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username); // For login
}
