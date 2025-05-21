package com.meuDiario.diary.controller.Login;

import com.meuDiario.diary.dto.login.SignUpUserDTO;
import com.meuDiario.diary.service.login.signUp.SignUpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/signUp")
public class SignUp {

    @Autowired
    private SignUpService service;

    @GetMapping
    public String signInPage(){
        return "Login/SignUp";
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@ModelAttribute("SignUpUserDTO") @Valid SignUpUserDTO dto){
        service.createUser(dto);
        return ResponseEntity.ok().build();
    }


}
