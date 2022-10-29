package com.example.JWTSecure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class IndexController {
    @RequestMapping("")
    public String hello() {
        return "Hello world";
    }
}
