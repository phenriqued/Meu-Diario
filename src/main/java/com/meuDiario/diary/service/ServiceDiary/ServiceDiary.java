package com.meuDiario.diary.service.ServiceDiary;

import com.meuDiario.diary.infra.Security.AuthenticatedUser.UserAuthentication;
import com.meuDiario.diary.model.Diary.Diary;
import com.meuDiario.diary.model.User.User;
import com.meuDiario.diary.repository.DiaryRepository.DiaryRepository;
import com.meuDiario.diary.repository.UserRepository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceDiary {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public ServiceDiary(DiaryRepository diaryRepository, UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
    }

    public void saveDiaryNotes(String text, UserAuthentication userAuthentication){
        User user = findByUser(userAuthentication);
        diaryRepository.save(new Diary(text, user));
    }

    public List<LocalDate> findDiaryDatesByUser(UserAuthentication userAuthentication) {
        User user = findByUser(userAuthentication);
        return diaryRepository.findDistinctDatesByUser(user);
    }


    public String findDiaryText(UserAuthentication userAuthentication, LocalDate selectDate) {
        User user = findByUser(userAuthentication);
        return diaryRepository.findTextByDateAndUser(user, selectDate).orElse("");
    }

    private User findByUser(UserAuthentication userAuthentication){
        return userRepository.findByNickname(userAuthentication.getUsername())
                .orElseThrow(() ->new UsernameNotFoundException("User cannot be found!"));
    }

}
