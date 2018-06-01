package com.springbootsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @GetMapping("/private/test")
    public List<String> test(){
        return new ArrayList<>();
    }

}
