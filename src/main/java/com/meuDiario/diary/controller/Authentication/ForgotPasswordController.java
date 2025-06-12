package com.meuDiario.diary.controller.Authentication;

import com.meuDiario.diary.dto.login.ForgotPassword.NewPasswordDTO;
import com.meuDiario.diary.service.Authentication.ForgotPassword.ForgotPasswordService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/esqueci-minha-senha")
public class ForgotPasswordController {

    private final String FORM_FORGOT_PASSWORD_PAGE = "/Authentication/FormForgotPassword";
    private final ForgotPasswordService service;

    public ForgotPasswordController(ForgotPasswordService service) {
        this.service = service;
    }

    @GetMapping
    public String getForgotPasswordPage(@RequestParam("code") String code, Model model){
        model.addAttribute("dados", new NewPasswordDTO("", ""));
        model.addAttribute("code", code);
        return FORM_FORGOT_PASSWORD_PAGE;
    }
    @PostMapping
    public String changePassword(@RequestParam("code") String code, @ModelAttribute("dados") @Valid NewPasswordDTO dados,
                                    Model model, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("dados", dados);
            return FORM_FORGOT_PASSWORD_PAGE;
        }
        try{
            service.changePassword(code, dados);
            return "redirect:signIn";
        }catch (UsernameNotFoundException e) {
            model.addAttribute("erro", e.getMessage());
            return FORM_FORGOT_PASSWORD_PAGE;
        }
    }

}
