package com.meuDiario.diary.dto.login.NumberAuthentication;

import jakarta.validation.constraints.NotBlank;

public record NumberPhoneDTO(
      @NotBlank
      String phoneNumber) {
    public NumberPhoneDTO(String phoneNumber){
        this.phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
    }
}
