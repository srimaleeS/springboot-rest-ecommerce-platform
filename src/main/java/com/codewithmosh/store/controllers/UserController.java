package com.codewithmosh.store.controllers;

import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Restfull API to fetch users from the db and return them in JSON format
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    //ResponseEntity is a Spring class that represents the entire HTTP response, including status code, headers, and body
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){ //PathVariable will allow to pass an ID dynamically to the route
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build(); //Return 404 Not Found if user is not found
        }
//        return new ResponseEntity<>(user,HttpStatus.OK);
        return ResponseEntity.ok(user); //Return 200 OK with the user object if found
    }
}
