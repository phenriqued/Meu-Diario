package com.meuDiario.diary.controller.HomePage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/home", "/", "index"})
public class HomePage {


    @GetMapping
    public String homepage(){
        return "HomePage/HomePage";
    }

}
