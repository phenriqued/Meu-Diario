package com.meuDiario.diary.service.Authentication.signUp;

import com.meuDiario.diary.dto.login.SignUp.SignUpUserDTO;
import com.meuDiario.diary.model.User.User;
import com.meuDiario.diary.repository.UserRepository.UserRepository;
import com.meuDiario.diary.service.SmsService.SmsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private UserRepository repository;
    private final SmsRequestService smsRequestService;

    public SignUpService(SmsRequestService smsRequestService) {
        this.smsRequestService = smsRequestService;
    }

    public User createUser(SignUpUserDTO dto){
        var user = repository.save(new User(dto));
        smsRequestService.sendTextSms(user);
        return user;
    }
}
