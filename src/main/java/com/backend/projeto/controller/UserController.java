package com.backend.projeto.controller;

import com.backend.projeto.entity.User;
import com.backend.projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> list(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User save(@RequestBody User user){
        return userRepository.save(user);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public User edit(@RequestBody User user){
        return userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<User> detail(@PathVariable Long id){
        return userRepository.findById(id);
    }
}
