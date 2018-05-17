package com.springbootsecurity.demo.service;

import com.springbootsecurity.demo.domain.User;

public interface UserService {
    public User findByUsername(String username);
}
