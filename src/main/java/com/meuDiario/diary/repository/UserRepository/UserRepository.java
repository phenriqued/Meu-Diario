package com.meuDiario.diary.repository.UserRepository;

import com.meuDiario.diary.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
