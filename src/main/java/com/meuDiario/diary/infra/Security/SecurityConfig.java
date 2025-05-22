package com.meuDiario.diary.infra.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/signIn", "/signUp", "/home", "/HelloWorld").permitAll()
                                .requestMatchers("/css/**", "/script/**", "/image/**").permitAll()
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form.loginPage("/signIn")
                                .defaultSuccessUrl("/home").permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/signIn?logout"))
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
