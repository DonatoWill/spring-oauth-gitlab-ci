package com.backend.projeto.config;

import com.backend.projeto.entity.User;
import com.backend.projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            this.createUsers("Donato", "donato_will@hotmail.com", "123456");
            this.createUsers("Donato William", "donato_will@hotmail.com", "123456");
        }
    }

    public void createUsers(String name, String email, String password) {
        User user = new User(name, email, password);
        userRepository.save(user);
    }

}
