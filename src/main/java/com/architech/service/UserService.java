package com.architech.service;

import com.architech.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
