package com.springbootsecurity.demo.configuration;

import com.springbootsecurity.demo.service.UserService;
import com.springbootsecurity.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    public void configAuth(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder pe = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.userDetailsService(userService).passwordEncoder(pe);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/private/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin();

        // just for H2 db, not for production
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
