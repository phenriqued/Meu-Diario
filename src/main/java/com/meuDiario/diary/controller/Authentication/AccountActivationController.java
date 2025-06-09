package com.meuDiario.diary.controller.Authentication;

import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import com.meuDiario.diary.service.Authentication.Activation.ActivationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/AtivarConta")
public class AccountActivationController {

    private final ActivationService service;
    private static final String ACCOUNT_ACTIVATION_PAGE = "Authentication/AccountActivationPage";

    public AccountActivationController(ActivationService service) {
        this.service = service;
    }

    @GetMapping
    public String getAccountActivationPage(@RequestParam(name = "nickname", required = false) String nickname, Model model){
        model.addAttribute("nickname", nickname);
        return ACCOUNT_ACTIVATION_PAGE;
    }

    @PostMapping
    public String activationAccount(@RequestParam(name = "nickname") String nickname,
                                    @ModelAttribute("tokenActivation") String tokenActivation,
                                    Model model, HttpServletRequest request){
        try{
            service.activeAccount(nickname, tokenActivation,request);
            return "redirect:diary";
        }catch (BusinnesRuleException e){
            model.addAttribute(e);
            return ACCOUNT_ACTIVATION_PAGE;
        }

    }
}
