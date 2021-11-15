package bot;

import commands.WeatherCordCommand;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Bot extends TelegramLongPollingBot {
    public Bot() {
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        if (message != null && message.hasLocation()) {
            var location = message.getLocation();
            var lat = location.getLatitude();
            var lon = location.getLongitude();
            var commandResult = new WeatherCordCommand().returnAnswerToLocation(lat.toString(), lon.toString());
            var splitAnswer = commandResult.split(System.lineSeparator());
            var icon = splitAnswer[splitAnswer.length - 1];
            var isFindIcon = icon.length() == 3;

            if (isFindIcon) {
                splitAnswer = Arrays.copyOf(splitAnswer, splitAnswer.length - 2);
                SendPhoto sendPhotoRequest = new SendPhoto();
                sendPhotoRequest.setChatId(message.getChatId().toString());
                var photoURL = "http://openweathermap.org/img/wn/" + icon + "@2x.png";
                sendPhotoRequest.setPhoto(photoURL);
                try {
                    sendPhoto(sendPhotoRequest);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            String messageTextResult = String.join(System.lineSeparator(), splitAnswer);
            sendMsg(message, messageTextResult, update);

        } else if (update.hasCallbackQuery()) {
            try {
                execute(new SendMessage().setText(
                                update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else {
            if (message != null && message.hasText()) {
                var messageText = message.getText();
                sendMsg(message, getAnswerToCommand(messageText, message), update);

            }

        }

    }


    public String getAnswerToCommand(String messageText, Message message)//Можем тестить этот метод
    {
        String answer;

        var commandTable = CommandTable.getTable();
        if (commandTable.containsKey(messageText.split(" ")[0])) {
            var answerDic = CommandTable.getItem(commandTable, messageText);

            if (answerDic.Icon != null) {
                SendPhoto sendPhotoRequest = new SendPhoto();
                sendPhotoRequest.setChatId(message.getChatId().toString());
                var photoURL = "http://openweathermap.org/img/wn/" + answerDic.Icon + "@2x.png";
                sendPhotoRequest.setPhoto(photoURL);
                try {
                    sendPhoto(sendPhotoRequest);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            answer = answerDic.Result;
        } else if (messageText.indexOf('/') != -1)
            answer = "Я не знаю, что тебе ответить, ты ввел неправильную комманду";
        else
            answer = "Извини, я пока не умею веести свободный диалог((( Попробуй ввести комманду";
        return answer;
    }

    private void sendMsg(Message message, String answer, Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(answer);
        sendMessage.setReplyMarkup(InlineKeyBoardMessage());


        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "DownloadAssistantBot";
    }

    @Override
    public String getBotToken() {
        Path path = Paths.get("token.txt");
        String contents = null;
        try {
            contents = Files.readString(path, StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
        }
        return contents;
    }

    @Override
    public void onClosing() {
        super.onClosing();
    }
    public static InlineKeyboardMarkup InlineKeyBoardMessage() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        inlineKeyboardButton1.setText("Рекомендации по погоде");
        inlineKeyboardButton1.setCallbackData("Рекомендуем одеться потеплее");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        //keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Рекомендации по погоде").setCallbackData("Оденьтесь потеплее во избежание"));
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
