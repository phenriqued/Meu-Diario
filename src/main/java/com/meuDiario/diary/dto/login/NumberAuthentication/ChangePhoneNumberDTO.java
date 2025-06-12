package com.meuDiario.diary.dto.login.NumberAuthentication;

import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import jakarta.validation.constraints.NotBlank;

public record ChangePhoneNumberDTO(
        @NotBlank
        String phoneNumber,
        @NotBlank
        String phoneNumberConfirmation) {

    public ChangePhoneNumberDTO(String phoneNumber, String phoneNumberConfirmation){
        this.phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        this.phoneNumberConfirmation = phoneNumberConfirmation.replaceAll("[^0-9]", "");
        if(!phoneNumber.equals(phoneNumberConfirmation)) throw new BusinnesRuleException("há diferenças entre os números!");
    }
}
