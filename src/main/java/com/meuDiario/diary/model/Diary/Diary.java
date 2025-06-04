package com.meuDiario.diary.model.Diary;

import com.meuDiario.diary.model.User.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_diary")

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String text;
    private LocalDate entryDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Diary(String text, User user) {
        this.text = text;
        this.user = user;
        this.entryDate = LocalDate.now();
    }
}
