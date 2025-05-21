package com.meuDiario.diary.infra.Security.SecurityService.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Codification {

    @Autowired
    private static BCryptPasswordEncoder encoder;


    public static String codification(String password){
        if (password.isBlank()) throw new IllegalStateException("encoding cannot be null!");
        encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
