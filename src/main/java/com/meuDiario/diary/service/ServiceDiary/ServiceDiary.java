package com.meuDiario.diary.service.ServiceDiary;

import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import com.meuDiario.diary.infra.Security.AuthenticatedUser.UserAuthentication;
import com.meuDiario.diary.model.Diary.Diary;
import com.meuDiario.diary.model.User.User;
import com.meuDiario.diary.repository.DiaryRepository.DiaryRepository;
import com.meuDiario.diary.repository.UserRepository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ServiceDiary {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public ServiceDiary(DiaryRepository diaryRepository, UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
    }

    public void saveOrUpdateDiaryNotes(LocalDate date, String text, UserAuthentication userAuthentication){
        User user = findByUser(userAuthentication);
        if(!diaryRepository.existsByEntryDate(date)){
            diaryRepository.save(new Diary(text, user));
            return;
        }
        Diary noteDiary = diaryRepository.findByEntryDateAndUser(date, user)
                            .orElseThrow(() -> new BusinnesRuleException("Notas do diário não foi encontrado."));
        noteDiary.setText(text);
        diaryRepository.flush();
    }

    public List<LocalDate> findDiaryDatesByUser(UserAuthentication userAuthentication) {
        User user = findByUser(userAuthentication);
        return diaryRepository.findDistinctDatesByUser(user);
    }


    public String findDiaryText(UserAuthentication userAuthentication, LocalDate selectDate) {
        User user = findByUser(userAuthentication);
        return diaryRepository.findTextByDateAndUser(user, selectDate).orElse("");
    }

    public void deleteNote(LocalDate data, UserAuthentication userAuthentication) {
        User user = findByUser(userAuthentication);
        Diary deleteDiary = diaryRepository.findByEntryDateAndUser(data, user).orElse(null);
        if (Objects.isNull(deleteDiary)) throw new BusinnesRuleException("Não é possível excluir uma nota do diário que não foi salva!");
        diaryRepository.delete(deleteDiary);
    }

    private User findByUser(UserAuthentication userAuthentication){
        return userRepository.findByNickname(userAuthentication.getUsername())
                .orElseThrow(() ->new UsernameNotFoundException("User cannot be found!"));
    }

}
