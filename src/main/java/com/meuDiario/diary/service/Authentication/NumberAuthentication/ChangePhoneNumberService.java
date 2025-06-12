package com.meuDiario.diary.service.Authentication.NumberAuthentication;

import com.meuDiario.diary.dto.login.NumberAuthentication.ChangePhoneNumberDTO;
import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import com.meuDiario.diary.infra.Security.AuthenticatedUser.UserAuthentication;
import com.meuDiario.diary.model.User.User;
import com.meuDiario.diary.repository.UserRepository.UserRepository;
import com.meuDiario.diary.service.SmsService.SmsRequestService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ChangePhoneNumberService {

    private final UserRepository userRepository;
    private final SmsRequestService smsRequestService;

    public ChangePhoneNumberService(UserRepository userRepository, SmsRequestService smsRequestService) {
        this.userRepository = userRepository;
        this.smsRequestService = smsRequestService;
    }

    public String verifyNumberPhone(ChangePhoneNumberDTO dados, UserAuthentication loggedUser) {
        User user = userRepository.findByNickname(loggedUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found!"));

        if(user.getPhoneNumber().equals(dados.phoneNumber())) throw new BusinnesRuleException("Não é possível alterar para o mesmo número atual!");
        user.setPhoneNumber(dados.phoneNumberConfirmation());
        user.setUuidTokenActivation();
        user.setEnable();
        smsRequestService.sendTextSms(user);
        userRepository.flush();
        return user.getNickname();
    }

}
