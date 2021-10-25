package Bot;

import Commands.WeatherCordCommand;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;


public class Bot extends TelegramLongPollingBot {
    //private final String BOT_NAME;
    //private final String BOT_TOKEN;

    /*
        public Bot(String bot_name, String bot_token) {
            super();
            //this.BOT_NAME = bot_name;
            //this.BOT_TOKEN = bot_token;
        }
    */
    public Bot() {
        //this.BOT_NAME = "";
        //this.BOT_TOKEN = "";
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
        if (message.hasLocation()) {
            var location = message.getLocation();
            var lat = location.getLatitude();
            var lon = location.getLongitude();
            sendMsg(message, new WeatherCordCommand().returnAnswerToLocation(lat.toString(), lon.toString()));
        } else {
            if (message != null && message.hasText()) {
                var messageText = message.getText();
                sendMsg(message, getAnswerToCommand(messageText, message));
            }
        }
    }

    public String getAnswerToCommand(String messageText, Message message)//Можем тестить этот метод
    {
        String answer;

        var commandTable = CommandTable.getTable();
        if (commandTable.containsKey(messageText.split("_")[0])) {
            var answerDic = CommandTable.getItem(commandTable, messageText);
            if (answerDic.containsKey("icon")) {
                SendPhoto sendPhotoRequest = new SendPhoto();
                sendPhotoRequest.setChatId(message.getChatId().toString());
                var photoURL = "http://openweathermap.org/img/wn/" + answerDic.get("icon") + "@2x.png";
                sendPhotoRequest.setPhoto(photoURL);
                try {
                    sendPhoto(sendPhotoRequest);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            answer = answerDic.get("result");

        } else if (messageText.indexOf('/') != -1)
            answer = "Я не знаю, что тебе ответить, ты ввел неправильную комманду";
        else
            answer = "Извини, я пока не умею веести свободный диалог((( Попробуй ввести комманду";
        return answer;
    }

    private void sendMsg(Message message, String answer) {
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
        return "овдпмоывдмоыдомдыловжфло";
    }

    @Override
    public void onClosing() {
        super.onClosing();
    }
}
