package com.meuDiario.diary.infra.Security.SecurityService.UserDetailsService;

import com.meuDiario.diary.infra.Security.AuthenticatedUser.UserAuthentication;
import com.meuDiario.diary.repository.UserRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String identify) throws UsernameNotFoundException {
        var user = repository.findByNickname(identify);
        if (user.isPresent()){
            return user.map(UserAuthentication::new).get();
        }

        user = repository.findByPhoneNumber(identify);
        if (user.isPresent()){
            return user.map(UserAuthentication::new).get();
        }
        throw new UsernameNotFoundException("User not found!");
    }
}
