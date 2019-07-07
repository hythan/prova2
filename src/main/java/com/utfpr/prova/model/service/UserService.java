package com.utfpr.prova.model.service;

import com.utfpr.prova.model.User;
import com.utfpr.prova.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public Optional<User> findById(long id) {
        return this.userRepository.findById(id);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }
}
