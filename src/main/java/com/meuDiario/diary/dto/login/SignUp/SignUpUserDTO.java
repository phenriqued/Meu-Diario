package com.meuDiario.diary.dto.login.SignUp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpUserDTO(

        @NotBlank
        @Size(min = 10, max = 11)
        String phoneNumber,
        @NotBlank
        @Size(min = 4, max = 20)
        String nickname,
        @NotBlank
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String password) {

        public SignUpUserDTO(String phoneNumber, String nickname, String password) {
                this.phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
                this.nickname = nickname;
                this.password = password;
        }
}

