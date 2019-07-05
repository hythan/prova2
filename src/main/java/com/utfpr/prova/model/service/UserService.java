package com.utfpr.prova.model.service;

import com.utfpr.prova.model.User;
import com.utfpr.prova.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> findAll(){
        return userRepository.findAll();
    }
}
