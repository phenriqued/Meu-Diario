package com.meuDiario.diary.repository.DiaryRepository;

import com.meuDiario.diary.model.Diary.Diary;
import com.meuDiario.diary.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("SELECT DISTINCT d.entryDate FROM Diary d WHERE d.user = :user ORDER BY d.entryDate DESC")
    List<LocalDate> findDistinctDatesByUser(User user);

    @Query("SELECT d.text FROM Diary d WHERE d.user = :user AND d.entryDate = :selectDate")
    Optional<String> findTextByDateAndUser(User user, LocalDate selectDate);

    Optional<Diary> findByEntryDateAndUser(LocalDate entryDate, User user);

    Boolean existsByEntryDateAndUser(LocalDate entryDate, User user);
}
