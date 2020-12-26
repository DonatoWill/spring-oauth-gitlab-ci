package com.backend.projeto.config;

import com.backend.projeto.entity.Role;
import com.backend.projeto.entity.User;
import com.backend.projeto.repository.RoleRepository;
import com.backend.projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            this.createUsers("Donato William", "admin", passwordEncoder.encode("1234"), "ROLE_ADMIN");
            this.createUsers("Donato", "donato_will@hotmail.com", passwordEncoder.encode("123456"), "ROLE_ALUNO");
        }
    }

    public void createUsers(String name, String email, String password, String role) {
        Role roleObject = new Role();
        roleObject.setName(role);

        this.roleRepository.save(roleObject);

        User user = new User(name, email, password, Arrays.asList(roleObject));
        userRepository.save(user);
    }

}
