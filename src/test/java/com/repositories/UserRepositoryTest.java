package com.repositories;

import com.model.persistence.User;
import com.model.persistence.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @Before
    public void setup() {
        user = new User();
        user.setUsername("test");
        user.setPassword("testPassword");
        userRepository.save(user);
    }

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void testFindByUsername(){
        User actual = userRepository.findByUsername("test");
        assertEquals(user.getUsername(), actual.getUsername());
    }

    @Test
    public void testFindById(){
        User actual = userRepository.findById(1L).get();
        assertEquals(user.getUsername(), actual.getUsername());
    }
}
