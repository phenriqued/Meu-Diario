package com.meuDiario.diary.controller.HelloWorld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/HelloWorld")

public class HelloWorld {

    @GetMapping
    public String Hello(){
        return "Hello/HelloWorld";
    }

}
