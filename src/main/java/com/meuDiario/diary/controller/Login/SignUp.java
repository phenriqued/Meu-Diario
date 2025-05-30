package com.meuDiario.diary.controller.Login;

import com.meuDiario.diary.dto.login.SignUpUserDTO;
import com.meuDiario.diary.service.login.signUp.SignUpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/signUp")
public class SignUp {

    @Autowired
    private SignUpService service;
    private static final String PAGE_LOGIN = "Login/SignUp";

    @GetMapping
    public String signInPage(){
        return PAGE_LOGIN;
    }

    @PostMapping
    public String createUser(@ModelAttribute("SignUpUserDTO") @Valid SignUpUserDTO dados, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("dados", dados);
            return PAGE_LOGIN;
        }
        service.createUser(dados);
        return "redirect:home";
    }


}
