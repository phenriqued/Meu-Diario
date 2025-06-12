package com.meuDiario.diary.controller.Authentication;

import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import com.meuDiario.diary.service.Authentication.Activation.ConfirmationAccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ConfirmationAccount")
public class ConfirmationAccountController {

    private final String CONFIRMATION_ACCOUNT_PAGE = "/Authentication/ConfirmationAccountPage";
    private final ConfirmationAccountService service;

    public ConfirmationAccountController(ConfirmationAccountService service) {
        this.service = service;
    }

    @GetMapping
    public String getPageConfirmationAccount(@RequestParam(value = "nickname", required = false) String nickname,
                                             @RequestParam(value = "activation") Boolean activation,
                                             Model model){
        model.addAttribute("nickname", nickname);
        model.addAttribute("activation", activation);
        return CONFIRMATION_ACCOUNT_PAGE;
    }

    @PostMapping
    public String verifyAccount(@RequestParam(value = "nickname") String nickname,
                                @RequestParam(value = "activation") Boolean activation,
                                @ModelAttribute("token") String token,
                                Model model, HttpServletRequest request){
        if(activation){
            try{
                service.activeAccount(nickname, token, request);
                return "redirect:diary";
            }catch (UsernameNotFoundException | BusinnesRuleException e){
                model.addAttribute("erroToken", e.getMessage());
                model.addAttribute("nickname", nickname);
                model.addAttribute("activation", activation);
                return CONFIRMATION_ACCOUNT_PAGE;
            }
        }
        try {
            String code = service.activeAccount(nickname,token);
            return "redirect:/esqueci-minha-senha?code="+code;
        }catch (UsernameNotFoundException | BusinnesRuleException e){
            model.addAttribute("erroToken", e.getMessage());
            model.addAttribute("nickname", nickname);
            model.addAttribute("activation", activation);
            return CONFIRMATION_ACCOUNT_PAGE;
        }
    }

    @GetMapping("/resend-code")
    public String resendTokenActivation(@RequestParam(name = "nickname") String nickname,
                                        @RequestParam(value = "activation") Boolean activation, Model model){
        try{
            model.addAttribute("nickname", nickname);
            model.addAttribute("activation", activation);
            service.resendToken(nickname);
            model.addAttribute("resendToken", "O token foi enviado novamente!");
            return CONFIRMATION_ACCOUNT_PAGE;
        }catch (BusinnesRuleException exception){
            model.addAttribute("erro",exception.getMessage());
            model.addAttribute("nickname", nickname);
            model.addAttribute("activation", activation);
            return CONFIRMATION_ACCOUNT_PAGE;
        }
    }

}
