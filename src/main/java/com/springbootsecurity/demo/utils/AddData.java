package com.springbootsecurity.demo.utils;

import com.springbootsecurity.demo.domain.Role;
import com.springbootsecurity.demo.domain.User;
import com.springbootsecurity.demo.repository.RoleRepository;
import com.springbootsecurity.demo.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AddData implements ApplicationListener<ContextRefreshedEvent> {


    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public AddData(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        save();
    }

    public void save(){

        User u = new User();
        u.setFullName("Hamza");
        u.setUsername("hzouitine");
        u.setPassword("hzouitine");
        u.setRoles(new ArrayList<>());
        String[] roles= new String[]{"ADMIN","USER","GUEST"};
        for(String s : roles) {
            Role role = new Role();
            role.setRole(s);
            u.getRoles().add(roleRepository.save(role));
        }
        userRepository.save(u);
    }

}
