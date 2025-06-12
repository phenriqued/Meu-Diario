package com.meuDiario.diary.service.Authentication.ForgotPassword;

import com.meuDiario.diary.dto.login.ForgotPassword.NewPasswordDTO;
import com.meuDiario.diary.model.User.User;
import com.meuDiario.diary.repository.UserRepository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {

    private final UserRepository userRepository;

    public ForgotPasswordService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void changePassword(String code, @Valid NewPasswordDTO dados) {
        User user = userRepository.findByTokenPassword(code)
                .orElseThrow(() -> new UsernameNotFoundException("User cannot be found!"));
        user.setPassword(dados.confirmPassword());
        user.clearTokenPassword();
        userRepository.flush();
    }
}
