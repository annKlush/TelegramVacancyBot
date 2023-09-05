package com.example.telegrambot.menu;

import com.example.telegrambot.VacanciesBot;
import com.example.telegrambot.dto.VacancyDto;
import com.example.telegrambot.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class LevelMenu {
    private static VacancyService vacancyService;
    @Autowired
    public LevelMenu(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }
    public static ReplyKeyboard getBackToVacanciesMenu() {
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton backToVacanciesButton = new InlineKeyboardButton();
        backToVacanciesButton.setText("Back to vacancies");
        backToVacanciesButton.setCallbackData("backToVacancies");
        row.add(backToVacanciesButton);

        InlineKeyboardButton backToStartMenuButton = new InlineKeyboardButton();
        backToStartMenuButton.setText("Back to start menu");
        backToStartMenuButton.setCallbackData("backToStartMenu");
        row.add(backToStartMenuButton);

        InlineKeyboardButton toChatGPT = new InlineKeyboardButton();
        toChatGPT.setText("Generate CV");
        toChatGPT.setUrl("https://chat.openai.com/");
        row.add(toChatGPT);

        return new InlineKeyboardMarkup(List.of(row));
    }

    public static ReplyKeyboard getJuniorVacanciesMenu() {
        List<VacancyDto> juniorVacancies = vacancyService.getJuniorVacancies();
        return getVacanciesMenu(juniorVacancies);
    }

    public static ReplyKeyboard getMiddleVacanciesMenu() {
        List<VacancyDto> middleVacancies = vacancyService.getMiddleVacancies();
        return getVacanciesMenu(middleVacancies);
    }

    public static ReplyKeyboard getSeniorVacanciesMenu() {
        List<VacancyDto> seniorVacancies = vacancyService.getSeniorVacancies();
        return getVacanciesMenu(seniorVacancies);
    }
    private static ReplyKeyboard getVacanciesMenu(List<VacancyDto> vacancies) {
        List<InlineKeyboardButton> row = new ArrayList<>();

        for (VacancyDto vacancy : vacancies) {
            InlineKeyboardButton vacancyButton = new InlineKeyboardButton();
            vacancyButton.setText(vacancy.getTitle());
            vacancyButton.setCallbackData("vacancyId=" + vacancy.getId());
            row.add(vacancyButton);
        }

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(List.of(row));
        return keyboard;
    }
}
