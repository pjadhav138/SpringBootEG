package com.monocep.todo_demo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class MyController {
	UserRepository repository;

	public MyController(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public String hello() {
		return "Hello World";
	}

	@GetMapping("/user/{id}")
	public EntityModel<User> retriveUser(@PathVariable("id") int id) {
		System.out.println("test");
		Optional<User> user = repository.findById(id);
		if (user.isEmpty())
			throw new UserNotFoundException("Error");
		EntityModel<User> entityModel = EntityModel.of(user.get());
		return entityModel;
	}

	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return repository.findAll();
	}

	@PostMapping("/addUser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		System.out.println("test"+user.getUsername());
		User savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();	
		System.out.println("test - location"+location);
		return ResponseEntity.created(location).build();
	}

}
