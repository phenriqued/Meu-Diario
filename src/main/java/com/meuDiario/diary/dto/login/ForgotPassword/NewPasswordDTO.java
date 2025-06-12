package com.meuDiario.diary.dto.login.ForgotPassword;

import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewPasswordDTO(
        @NotBlank
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String password,
        @NotBlank
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String confirmPassword) {

    public NewPasswordDTO{
        if(!password.equals(confirmPassword)) throw new BusinnesRuleException("As senhas tem que ser iguais");
    }
}
