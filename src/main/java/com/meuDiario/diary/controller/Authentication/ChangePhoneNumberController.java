package com.meuDiario.diary.controller.Authentication;


import com.meuDiario.diary.dto.login.NumberAuthentication.ChangePhoneNumberDTO;
import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import com.meuDiario.diary.infra.Security.AuthenticatedUser.UserAuthentication;
import com.meuDiario.diary.service.Authentication.NumberAuthentication.ChangePhoneNumberService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alterar-numero")
public class ChangePhoneNumberController {

    private final String PAGE_FORM_CHANGE_PHONE_NUMBER = "/Authentication/FormChangePhoneNumber";
    private final ChangePhoneNumberService service;

    public ChangePhoneNumberController(ChangePhoneNumberService service) {
        this.service = service;
    }

    @GetMapping
    public String loadPageFormChangePhoneNumber(Model model){
        model.addAttribute("phoneNumberData", new ChangePhoneNumberDTO("",""));
        return PAGE_FORM_CHANGE_PHONE_NUMBER;
    }

    @PostMapping
    public String confirmationPhoneNumber(@ModelAttribute("phoneNumberData") @Valid ChangePhoneNumberDTO phoneNumberData,
                                          @AuthenticationPrincipal UserAuthentication loggedUser,
                                          Model model, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("phoneNumberData", phoneNumberData);
            return PAGE_FORM_CHANGE_PHONE_NUMBER;
        }
        try{
            String user = service.verifyNumberPhone(phoneNumberData, loggedUser);
            return "redirect:/ConfirmationAccount?nickname="+user+"&activation="+Boolean.TRUE;
        }catch (UsernameNotFoundException | BusinnesRuleException e){
            model.addAttribute("erro", e.getMessage());
            return "redirect:/alterar-numero?error=true";
        }

    }

}
