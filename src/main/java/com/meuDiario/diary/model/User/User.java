package com.meuDiario.diary.model.User;


import com.meuDiario.diary.dto.login.SignUp.SignUpUserDTO;
import com.meuDiario.diary.infra.Security.SecurityService.Encoder.Codification;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Setter
    @Column(unique = true, nullable = false, length = 20)
    private String nickname;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false, length = 11)
    private String phoneNumber;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(unique = true)
    private String uuidTokenActivation;
    @Column
    private LocalDateTime uuidTokenExpiration;
    @Column(nullable = false)
    private Boolean isEnable;
    @Column(unique = true, length = 36)
    private String tokenPassword;

    public User(SignUpUserDTO dto) {
        this.nickname = dto.nickname();
        this.password = Codification.codification(dto.password());
        this.phoneNumber = dto.phoneNumber();
        this.createdAt = LocalDateTime.now();
        this.isEnable = false;
        setUuidTokenActivation();
    }

    public void setEnable() {
        this.isEnable = !isEnable;
    }

    public void setUuidTokenActivation(){
        this.uuidTokenActivation = UUID.randomUUID().toString().substring(0, 5);
        this.uuidTokenExpiration = LocalDateTime.now().plusMinutes(30);
    }
    public void clearTokenActivation(){
        this.uuidTokenExpiration = null;
        this.uuidTokenActivation = null;
    }
    public void setTokenPassword(){
        this.tokenPassword = UUID.randomUUID().toString().replaceAll("-", String.valueOf(nickname.charAt(0)));
    }
    public void clearTokenPassword(){
        this.tokenPassword = null;
    }
    public void setPassword(String newPassword){
        if(Objects.isNull(newPassword)) throw new NullPointerException("The new password cannot be null");
        this.password = Codification.codification(newPassword);
    }

}
