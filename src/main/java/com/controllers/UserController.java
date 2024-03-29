package com.controllers;

import com.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.persistence.Cart;
import com.model.persistence.User;
import com.model.persistence.repositories.CartRepository;
import com.model.persistence.repositories.UserRepository;
import com.model.requests.CreateUserRequest;

@RestController
//@Transactional
@RequestMapping("/api/user")
//@EnableJpaRepositories
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/id/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
//		Optional<User> optional = userRepository.findById(id);
		return ResponseEntity.of(userRepository.findById(id));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUserName(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
	}

//	@Transactional
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
//
		User user = new User();
		user.setUsername(createUserRequest.getUsername());
		user.setPassword(createUserRequest.getPassword());
		log.info("User name set with " + createUserRequest.getUsername());
		Cart cart = new Cart();
		cartRepository.save(cart);
		user.setCart(cart);
		if(createUserRequest.getPassword().length() < 7 ||
		!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
			log.error("Error with user password. Cannot create user {}", createUserRequest.getUsername());
		}
		user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
		userRepository.save(user);
//		userService.saveUser(user);
		log.info("User " + user.getUsername() + " is saved.");
		return ResponseEntity.ok(user);
	}
	
}
