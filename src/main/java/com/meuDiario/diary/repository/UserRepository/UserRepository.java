package com.meuDiario.diary.repository.UserRepository;

import com.meuDiario.diary.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(String nickname);
    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByUuidTokenActivation(String tokenActivation);

    Optional<User> findByTokenPassword(String code);
}
