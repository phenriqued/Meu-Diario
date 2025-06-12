package com.meuDiario.diary.service.Authentication.NumberAuthentication;

import com.meuDiario.diary.dto.login.NumberAuthentication.NumberPhoneDTO;
import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import com.meuDiario.diary.model.User.User;
import com.meuDiario.diary.repository.UserRepository.UserRepository;
import com.meuDiario.diary.service.SmsService.SmsRequestService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class NumberVerificationService {

    private final UserRepository userRepository;
    private final SmsRequestService smsRequestService;

    public NumberVerificationService(UserRepository userRepository, SmsRequestService smsRequestService) {
        this.userRepository = userRepository;
        this.smsRequestService = smsRequestService;
    }

    public String getVerifyNumber(@Valid NumberPhoneDTO number) {
        User user = userRepository.findByPhoneNumber(number.phoneNumber())
                .orElseThrow(() ->
                        new BusinnesRuleException("Verifique o número digitado. O número: "+ number.phoneNumber()+" não corresponde a usuário válido."));
        user.setUuidTokenActivation();
        //smsRequestService.sendChangePasswordTextSms(user);
        System.out.println("\nO CÓDIGO PARA ALTERAR A SENHA: "+user.getUuidTokenActivation()+"\n");
        userRepository.flush();
        return user.getNickname();
    }
}
