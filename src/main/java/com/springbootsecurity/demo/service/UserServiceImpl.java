package com.springbootsecurity.demo.service;

import com.springbootsecurity.demo.domain.Role;
import com.springbootsecurity.demo.domain.User;
import com.springbootsecurity.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent())
            return user.get();
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        user.getRoles().forEach(role -> grantedAuthorityList.add(new SimpleGrantedAuthority(role.getRole())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorityList);
    }
}
