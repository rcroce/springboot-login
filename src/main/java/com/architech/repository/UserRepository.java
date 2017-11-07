package com.architech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.architech.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
