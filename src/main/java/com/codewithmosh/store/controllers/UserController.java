package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.UserDto;
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
    public Iterable<UserDto> getAllUsers(){
        return userRepository.findAll().stream().map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .toList(); //Convert the list of User entities to a list of UserDto objects
    }

    //ResponseEntity is a Spring class that represents the entire HTTP response, including status code, headers, and body
    //PathVariable will allow to pass an ID dynamically to the route
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build(); //Return 404 Not Found if user is not found
        }
//        return new ResponseEntity<>(user,HttpStatus.OK);
        var userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userDto); //Return 200 OK with the user object if found
    }
}
