package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

//Restfull API to fetch users from the db and return them in JSON format
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers(
        @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ){
        if (!Set.of("name","email").contains(sort))
            sort= "name"; //Default sort by name if the sort parameter is not valid
        return userRepository.findAll(Sort.by(sort))
                .stream()
//                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .map(user -> userMapper.toDto(user)) //Use the UserMapper to convert User entities to UserDto objects instead of mapping it manually, can also be written as map(userMapper::toDto)
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
        return ResponseEntity.ok(userMapper.toDto(user)); //Return 200 OK with the user object if found
    }
}
