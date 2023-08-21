package com.shopping.mypet.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {


    @GetMapping("/http/get")
    public String getTest(@RequestParam int id) {
        return "get Test" + id;
    }

    @PostMapping("/http/post")
    public String postTest() {
        return "post 요청";
    }
}
