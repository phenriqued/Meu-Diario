package com.meuDiario.diary.infra.Security.FailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String redirectUrl = "/signIn?error";

        if(exception instanceof DisabledException){
            String username = request.getParameter("username");
            if (username != null && !username.isEmpty()) {
                redirectUrl = "/AtivarConta?nickname=" + username;
            }
        }else {
            redirectUrl = "/signIn?error=true";
        }
        response.sendRedirect(redirectUrl);
    }
}
