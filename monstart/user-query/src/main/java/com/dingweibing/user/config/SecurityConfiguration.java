package com.dingweibing.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("bob")
                .password("alice")
                .roles("USER")
                .build();
        jdbcUserDetailsManager.createUser(user);
        user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("pwd")
                .roles("USER")
                .build();
        jdbcUserDetailsManager.createUser(user);
        return jdbcUserDetailsManager;
    }
}
