package com.example.telegrambot.menu;

import com.example.telegrambot.Level;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartMenu {
    public static ReplyKeyboard getStartMenu() {
      List<InlineKeyboardButton> row = new ArrayList<>();
      List<Level> levels = Arrays.asList(Level.values());
      InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
      for (Level level : levels) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(level.getLevel());
        button.setCallbackData("show" + level.getLevel() + "Vacancies");
        row.add(button);
      }
        InlineKeyboardMarkup keybord = new InlineKeyboardMarkup();
        keybord.setKeyboard(List.of(row));
        return keybord;
    }
}
