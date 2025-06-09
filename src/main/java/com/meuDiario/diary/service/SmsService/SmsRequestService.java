package com.meuDiario.diary.service.SmsService;

import com.meuDiario.diary.model.User.User;
import org.springframework.stereotype.Service;

@Service
public class SmsRequestService {

    private final SMSService smsService;

    public SmsRequestService(SMSService smsService) {
        this.smsService = smsService;
    }

    public void sendTextSms(User user){
        String message = gerarMessage(
                """
                Olá [[nickname]]. O seu código de verificação para Diário é: [[token]]
                """, user.getNickname(), user.getUuidTokenActivation());
        smsService.sendSMS(verifyNumber(user.getPhoneNumber()), message);
    }

    private String gerarMessage(String template, String nickname, String token){
        return template.replace("[[nickname]]", nickname).replace("[[token]]", token);
    }
    private String verifyNumber(String number){
        if (number.startsWith("+55")) return number;
        return "+55"+number;
    }

}
