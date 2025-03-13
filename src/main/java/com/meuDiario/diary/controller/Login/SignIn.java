package com.meuDiario.diary.controller.Login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/signIn")
public class SignIn {


    @GetMapping
    public String signInPage(){
        return "Login/SignIn";
    }


}
