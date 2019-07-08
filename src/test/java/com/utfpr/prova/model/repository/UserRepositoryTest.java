package com.utfpr.prova.model.repository;

import com.utfpr.prova.model.User;
import com.utfpr.prova.security.ProfileEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    public UserRepositoryTest(){

    }

    @Before
    public void setUp(){
        User user = new User("dwaudhuw@gmail.com","abc123", ProfileEnum.ROLE_USER,"dkawjdb");
        userRepository.save(user);
    }

    @After
    public void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    public void findByEmail(){
        User user = userRepository.findByEmail("dwaudhuw@gmail.com");
        assertFalse(user == null);
    }
}
