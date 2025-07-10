package com.codewithmosh.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//Marking this class as a bean with this annotation, a bean is an object that is
// managed by Spring (Creates an instance of this class and manages it)
@Controller
public class HomeController {
    @RequestMapping("/") //The root of the website
    public String index(Model model){
        model.addAttribute("name","Siri");
        return "index";
    }

//    @RequestMapping("/hello") //The root of the website
//    public String sayHello(){
//        return "index.html";
//    }
}
