package com.meuDiario.diary.service.Authentication.Activation;

import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import com.meuDiario.diary.infra.Security.AuthenticatedUser.UserAuthentication;
import com.meuDiario.diary.model.User.User;
import com.meuDiario.diary.repository.UserRepository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ActivationService {

    private final UserRepository userRepository;

    public ActivationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void activeAccount(String nickname, String tokenActivation,HttpServletRequest request) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        User userByToken = userRepository.findByUuidTokenActivation(tokenActivation)
                .orElseThrow(() -> new UsernameNotFoundException("User by UUID TOKEN not found!"));

        if(!user.getUuidTokenActivation().equals(userByToken.getUuidTokenActivation()))
            throw new BusinnesRuleException("the token is invalid!");
        if(userByToken.getUuidTokenExpiration().isBefore(LocalDateTime.now()))
            throw new BusinnesRuleException("the token is expired!!");

        user.setEnable();
        user.clearTokenActivation();
        userRepository.save(user);
        logarUser(new UserAuthentication(user), request);
    }

    private void logarUser(UserAuthentication user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }
}
