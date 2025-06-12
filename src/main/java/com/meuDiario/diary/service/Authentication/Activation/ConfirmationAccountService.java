package com.meuDiario.diary.service.Authentication.Activation;

import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import com.meuDiario.diary.infra.Security.AuthenticatedUser.UserAuthentication;
import com.meuDiario.diary.model.User.User;
import com.meuDiario.diary.repository.UserRepository.UserRepository;
import com.meuDiario.diary.service.SmsService.SmsRequestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ConfirmationAccountService {

    private final UserRepository userRepository;
    private final SmsRequestService smsRequestService;

    public ConfirmationAccountService(UserRepository userRepository, SmsRequestService smsRequestService) {
        this.userRepository = userRepository;
        this.smsRequestService = smsRequestService;
    }

    public void activeAccount(String nickname, String tokenActivation,HttpServletRequest request) {
        User user = findByNickname(nickname, "Usuário não encontrado, tente Login fazer o novamente");
        User userByToken = userRepository.findByUuidTokenActivation(tokenActivation)
                .orElseThrow(() -> new UsernameNotFoundException("Token inexistente!"));

        verifyTokenAndExpiration(user, userByToken);

        user.setEnable();
        user.clearTokenActivation();
        userRepository.save(user);
        logarUser(new UserAuthentication(user), request);
    }

    public String activeAccount(String nickname, String token) {
        User user = findByNickname(nickname, "Usuário não encontrado, tente Login fazer o novamente");
        User userByToken = userRepository.findByUuidTokenActivation(token)
                .orElseThrow(() -> new UsernameNotFoundException("Token inexistente!"));
        verifyTokenAndExpiration(user, userByToken);
        user.clearTokenActivation();
        user.setTokenPassword();
        userRepository.save(user);
        return user.getTokenPassword();
    }

    public void resendToken(String nickname) {
        var user = findByNickname(nickname, "[ERROR] User not found!");

        if(Objects.isNull(user.getUuidTokenActivation())) throw new BusinnesRuleException("Token is null");

        if(user.getUuidTokenExpiration().isAfter(LocalDateTime.now())) {
            var shippingTime = user.getUuidTokenExpiration().minusMinutes(LocalDateTime.now().getMinute()).getMinute();
            throw new BusinnesRuleException("O Token não expirou, aguarde "+shippingTime+" minutos para poder reenviar o código!");
        }

        user.clearTokenActivation();
        user.setUuidTokenActivation();
        smsRequestService.sendTextSms(user);
        userRepository.save(user);
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
    private User findByNickname(String nickname, String message){
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException(message));
    }

    private void verifyTokenAndExpiration(User user, User userByToken){
        if(!user.getUuidTokenActivation().equals(userByToken.getUuidTokenActivation()))
            throw new BusinnesRuleException("O Token está inválido!");
        if(userByToken.getUuidTokenExpiration().isBefore(LocalDateTime.now()))
            throw new BusinnesRuleException("O Token expirou, clique em enviar um novo token!");
    }


}
