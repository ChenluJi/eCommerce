package com.services;

import com.model.persistence.User;
import com.model.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveUser(User user){
        userRepository.saveAndFlush(user);
    }
    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> retrieveUserById(Long id){
        Optional<User> optional = userRepository.findById(id);
//        User user = optional.orElseThrow(UserNotFoundException::new);
//        User user = optional.get();
        return optional;
    }
//
//    public List<String> retrieveDogNames(){
//        return (List<String>) dogRepository.findAllName();
//    }
}
