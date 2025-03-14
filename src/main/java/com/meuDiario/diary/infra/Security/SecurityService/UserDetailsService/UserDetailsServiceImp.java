package com.meuDiario.diary.infra.Security.SecurityService.UserDetailsService;

import com.meuDiario.diary.infra.Security.UserAuthentication.UserAuthentication;
import com.meuDiario.diary.repository.UserRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        if (userRepository.existsByPhoneNumber(identifier)) {
            return
                    userRepository.findByPhoneNumber(identifier).map(UserAuthentication::new)
                            .orElseThrow();
        }else{
            return
                    userRepository.findByNickname(identifier).map(UserAuthentication::new)
                            .orElseThrow();
        }
    }
}
