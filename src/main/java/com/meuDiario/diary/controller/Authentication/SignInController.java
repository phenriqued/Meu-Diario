package com.meuDiario.diary.controller.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/signIn")
public class SignInController {


    @GetMapping
    public String signInPage(){
        return "Authentication/SignIn";
    }


}
