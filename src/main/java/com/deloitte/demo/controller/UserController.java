package com.deloitte.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.demo.enitity.User;
import com.deloitte.demo.exception.ResourceNotFoundException;
import com.deloitte.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public User getUsersById(@PathVariable(value="id") long userId){
		return this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with id "+userId));		
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	
	@PutMapping("{/id}")
	public User updateUser(@RequestBody User user, @PathVariable ("id") long userId) {
		
		User existing = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException ("User not avaialble"+ userId));
		existing.setFirstName(user.getFirstName());
		existing.setLastName(user.getLastName());
		existing.setLastName(user.getEmail());
		return this.userRepository.save(existing);
	}
	
	@DeleteMapping("{/id}")
	public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
		
		User existing = this.userRepository.findById(userId).orElseThrow(()-> new  ResourceNotFoundException ("USer not available"+userId));
		this.userRepository.delete(existing);
		return  ResponseEntity.ok().build();
	}
}
