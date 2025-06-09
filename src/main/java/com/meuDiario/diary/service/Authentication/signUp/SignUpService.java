package com.meuDiario.diary.service.Authentication.signUp;

import com.meuDiario.diary.dto.login.SignUpUserDTO;
import com.meuDiario.diary.model.User.User;
import com.meuDiario.diary.repository.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private UserRepository repository;

    public User createUser(SignUpUserDTO dto){
        return repository.save(new User(dto));
    }


}
