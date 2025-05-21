package com.meuDiario.diary.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpUserDTO(

        @NotBlank
        @Size(min = 10, max = 11)
        @Pattern(regexp = "^(?:\\+?55)?(?:\\(?\\d{2}\\)?)?\\s?9?\\d{4}-?\\d{4}$")
        String phoneNumber,
        @NotBlank
        @Size(min = 4, max = 20)
        String nickname,
        @NotBlank
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String password) {
}
