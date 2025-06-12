package com.meuDiario.diary.controller.Authentication;

import com.meuDiario.diary.dto.login.NumberAuthentication.NumberPhoneDTO;
import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import com.meuDiario.diary.service.Authentication.NumberAuthentication.NumberVerificationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/verificar-numero")
public class NumberVerificationController {

    private final String CONFIRMATION_NUMBER_PAGE = "/Authentication/ConfirmationNumber";
    private final NumberVerificationService service;

    public NumberVerificationController(NumberVerificationService service) {
        this.service = service;
    }

    @GetMapping
    public String getConfirmationNumberUserPage(Model model){
        model.addAttribute("number", new NumberPhoneDTO(""));
        return CONFIRMATION_NUMBER_PAGE;
    }
    @PostMapping
    public String getNumberUser(@ModelAttribute("number") @Valid NumberPhoneDTO number, Model model, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("number", number);
            return CONFIRMATION_NUMBER_PAGE;
        }
        try{
            String user = service.getVerifyNumber(number);
            return "redirect:/ConfirmationAccount?nickname="+user+"&activation="+Boolean.FALSE;
        }catch (BusinnesRuleException e){
            model.addAttribute("erro", e);
            return CONFIRMATION_NUMBER_PAGE;
        }
    }


}
