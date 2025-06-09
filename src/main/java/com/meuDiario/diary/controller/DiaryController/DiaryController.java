package com.meuDiario.diary.controller.DiaryController;

import com.meuDiario.diary.infra.Exception.BusinnesRuleException.BusinnesRuleException;
import com.meuDiario.diary.infra.Security.AuthenticatedUser.UserAuthentication;
import com.meuDiario.diary.service.ServiceDiary.ServiceDiary;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/diary")
public class DiaryController {

    private final ServiceDiary service;
    private static final String PAGE_MEU_DIARIO = "DiaryPage/DiaryPage";

    public DiaryController(ServiceDiary service) {
        this.service = service;
    }

    @GetMapping
    public String loadDiaryPage(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                                Model model, @AuthenticationPrincipal UserAuthentication user){
        List<LocalDate> dates = service.findDiaryDatesByUser(user);
        LocalDate hoje = LocalDate.now();
        LocalDate selectDate = data != null ? data : LocalDate.now();

        String text = service.findDiaryText(user, selectDate);

        model.addAttribute("datas",dates);
        model.addAttribute("hoje", hoje);
        model.addAttribute("dataSelecionada", selectDate);
        model.addAttribute("texto", text);
        return PAGE_MEU_DIARIO;
    }

    @PostMapping("/save-note-diary")
    public String saveDiaryOrUpdate(@ModelAttribute("texto") String text,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                            @AuthenticationPrincipal UserAuthentication user){
        if(text.isEmpty()) return "redirect:/diary?verificarText=true";
        service.saveOrUpdateDiaryNotes(data, text, user);
        return "redirect:/diary?verificar=true";
    }

    @PostMapping("/delete-note")
    public String deleteDiaryNote(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                                  @AuthenticationPrincipal UserAuthentication user, Model model){
        try{
            service.deleteNote(data, user);
            return "redirect:/diary?excluirSucess=true";
        } catch (BusinnesRuleException e) {
            model.addAttribute("texto", "");
            return "redirect:/diary?excluir=false";
        }
    }

}
