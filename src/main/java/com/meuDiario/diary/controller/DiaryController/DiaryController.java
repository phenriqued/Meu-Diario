package com.meuDiario.diary.controller.DiaryController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/diary")
public class DiaryController {

    @GetMapping
    public String loadDiaryPage(){
        return "DiaryPage/DiaryPage";
    }

}
