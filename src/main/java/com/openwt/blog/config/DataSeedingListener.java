package com.openwt.blog.config;

import com.openwt.blog.model.user.Role;
import com.openwt.blog.model.user.User;
import com.openwt.blog.repository.RoleRepository;
import com.openwt.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
@Configuration
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private void addRoleIfMissing(String name, String description) {
        if (roleRepository.findByName(name) == null) {
            roleRepository.save(new Role(name, description));
        }
    }

    public void addUserIfMissing(String username, String firstname, String lastname, String password, String... roles) {
        if (userRepository.findByEmail(username) == null) {
            User user = new User(username, firstname, lastname, new BCryptPasswordEncoder().encode(password));
            user.setRoles(new HashSet<>());
            for (String role : roles) {
                user.getRoles().add(roleRepository.findByName(role));
            }
            userRepository.save(user);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addRoleIfMissing("ROLE_ADMIN", "Administrators");
        addRoleIfMissing("ROLE_USER", "Users");

        addUserIfMissing("user@gmail.com", "Kim", "Ho", "password", "ROLE_USER");
        addUserIfMissing("admin@gmail.com", "Admin", "Spring", "password", "ROLE_ADMIN", "ROLE_USER");
    }
}
