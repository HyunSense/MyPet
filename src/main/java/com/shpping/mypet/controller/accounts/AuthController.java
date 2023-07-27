package com.shpping.mypet.controller.accounts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/accounts")
public class AuthController {

    @GetMapping("/login")
    public String loginGetForm() {

        return "/view/accounts/login2";
    }

    @PostMapping("/login")
    @ResponseBody
    public String loginPostForm() {

        return "Login Test";
    }

}
