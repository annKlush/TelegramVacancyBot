package com.example.telegrambot;

import com.example.telegrambot.dto.VacancyDto;
import com.example.telegrambot.menu.LevelMenu;
import com.example.telegrambot.menu.StartMenu;
import com.example.telegrambot.service.VacancyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;


@Component
public class VacanciesBot extends TelegramLongPollingBot {
    @Autowired
    private VacancyService vacancyService;

    private final Map<Long, String> lastShownLevel = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.getMessage() != null) {
                handleStartCommand(update);
            }
            if (update.getCallbackQuery() != null) {
                String callbackData = update.getCallbackQuery().getData();

                if ("showJuniorVacancies".equals(callbackData)) {
                    showJuniorVacancies(update);
                } else if ("showMiddleVacancies".equals(callbackData)) {
                    showMiddleVacancies(update);
                } else if ("showSeniorVacancies".equals(callbackData)) {
                    showSeniorVacancies(update);
                } else if (callbackData.startsWith("vacancyId=")) {
                    String id = callbackData.split("=")[1];
                    showVacancyDescription(id, update);
                } else if ("backToVacancies".equals(callbackData)) {
                    handleBackToVacanciesCommand(update);
                } else if ("backToStartMenu".equals(callbackData)) {
                    handleBackStartCommand(update);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Can`t send message to user!", e);
        }
    }

    private void handleBackStartCommand(Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Choose title:");
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        sendMessage.setReplyMarkup(StartMenu.getStartMenu());
        execute(sendMessage);
    }

    private void handleStartCommand(Update update) {
        String text = update.getMessage().getText();
        System.out.println("Text " + text);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Welcome to vacancies bot! Please, choose your title:");
        sendMessage.setReplyMarkup(StartMenu.getStartMenu());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleBackToVacanciesCommand(Update update) throws TelegramApiException {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String level = lastShownLevel.get(chatId);
        if ("junior".equals(level)) {
            showJuniorVacancies(update);
        } else if ("middle".equals(level)) {
            showMiddleVacancies(update);
        } else if ("senior".equals(level)) {
            showSeniorVacancies(update);
        }
    }

    private void showVacancyDescription(String id, Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        VacancyDto vacancy = vacancyService.get(id);
        String company=vacancy.getCompany();
        String shortDescription = vacancy.getShortDescription();
        String salary=vacancy.getSalary();
        String longDesc = vacancy.getLongDesc();
        String link=vacancy.getLink();
        sendMessage.setText("Company name: "+company+" \n\n"+shortDescription+". \n\n"+longDesc+" \nsalary: "+salary+" \nLink: "+link);
        sendMessage.setReplyMarkup(LevelMenu.getBackToVacanciesMenu());
        execute(sendMessage);
    }

    private void showJuniorVacancies(Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Please, choose vacancy");
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(LevelMenu.getJuniorVacanciesMenu());
        execute(sendMessage);
        lastShownLevel.put(chatId, "junior");
    }

    private void showMiddleVacancies(Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Please, choose vacancy");
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(LevelMenu.getMiddleVacanciesMenu());
        execute(sendMessage);
        lastShownLevel.put(chatId, "middle");
    }

    private void showSeniorVacancies(Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Please, choose vacancy");
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(LevelMenu.getSeniorVacanciesMenu());
        execute(sendMessage);
        lastShownLevel.put(chatId, "senior");
    }



    public VacanciesBot() {
        super(Constant.getBotToken());
    }

    @Override
    public String getBotUsername() {
        return Constant.getBotName();
    }
}
