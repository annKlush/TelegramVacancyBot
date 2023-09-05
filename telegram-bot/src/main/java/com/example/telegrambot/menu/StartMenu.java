package com.example.telegrambot.menu;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartMenu {
    public static ReplyKeyboard getStartMenu() {
      List<InlineKeyboardButton> row = new ArrayList<>();
     /* List<Level> levels = Arrays.asList(Level.values());
      InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
      List<InlineKeyboardButton> row = new ArrayList<>();
      for (Level level : levels) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(level.getLevel());
        button.setCallbackData("show" + level.getLevel() + "Vacancies");
        row.add(button);
      }*/
      InlineKeyboardButton junior = new InlineKeyboardButton();
        junior.setText("Junior");
        junior.setCallbackData("showJuniorVacancies");
        row.add(junior);

        InlineKeyboardButton middle = new InlineKeyboardButton();
        middle.setText("Middle");
        middle.setCallbackData("showMiddleVacancies");
        row.add(middle);

        InlineKeyboardButton senior = new InlineKeyboardButton();
        senior.setText("Senior");
        senior.setCallbackData("showSeniorVacancies");
        row.add(senior);

        InlineKeyboardMarkup keybord = new InlineKeyboardMarkup();
        keybord.setKeyboard(List.of(row));
        return keybord;
    }
}
