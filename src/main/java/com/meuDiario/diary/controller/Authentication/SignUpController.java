package com.meuDiario.diary.controller.Authentication;

import com.meuDiario.diary.dto.login.SignUpUserDTO;
import com.meuDiario.diary.service.Authentication.signUp.SignUpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/signUp")
public class SignUpController {

    @Autowired
    private SignUpService service;
    private static final String PAGE_LOGIN = "Authentication/SignUp";

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
        var user = service.createUser(dados);
        return "redirect:AtivarConta?nickname="+user.getNickname();
    }


}
