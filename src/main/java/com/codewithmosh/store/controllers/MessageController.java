package com.codewithmosh.store.controllers;

import com.codewithmosh.store.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @RequestMapping("/hello")

//'View page source' on the browser will only display the text
//    public String sayHello() {
//        return "Hello RestController!";
//    }

//The browser will display of the following Java object as a JSON object (key-value pair)
    public Message sayHello(){
        return new Message("Hello RestController in JSON format");
    }
}
