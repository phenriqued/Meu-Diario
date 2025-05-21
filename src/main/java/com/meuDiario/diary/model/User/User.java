package com.meuDiario.diary.model.User;


import com.meuDiario.diary.dto.login.SignUpUserDTO;
import com.meuDiario.diary.infra.Security.SecurityService.Encoder.Codification;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    public User(SignUpUserDTO dto) {
        this.nickname = dto.nickname();
        this.password = Codification.codification(dto.password());
        this.phoneNumber = dto.phoneNumber();
        this.createdAt = LocalDateTime.now();
    }
}
