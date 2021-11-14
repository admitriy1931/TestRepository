package bot;

import commands.WeatherCordCommand;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;

import java.util.Arrays;


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


        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "att1000_bot";
    }

    @Override
    public String getBotToken() {
        return "1987090518:AAFbMTQ6R1HEfQh91RoPSCZ2Ee93HcRl5x8";
    }

    @Override
    public void onClosing() {
        super.onClosing();
    }
}
