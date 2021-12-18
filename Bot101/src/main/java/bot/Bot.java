package bot;

import advisor.Recommendation;
import commands.JsonParserResult;
import commands.ParserOutput;
import commands.SimpleBotCommand;
import commands.WeatherCordCommand;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVoice;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.ByteArrayInputStream;
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

    static String START_OF_PHOTO_URL =
            "http://openweathermap.org/img/wn/";
    static String END_OF_PHOTO_URL = "@2x.png";
    static String YANDEX_TOKEN = "t1.9euelZqLjc7MjomUk4zIypDOlpqJmO3rnpWayo6Lis-Lns6UnpuLzMrLkYnl8_d-Qgxy" +
            "-e9eAHUf_t3z9z5xCXL5714AdR_-.t6pbRzRmlVEm8S7_FBv2jA5-_XtU0oOsjYPx8xQP5zt3xtxoT3pJyEIBvmN" +
            "-axfzWRcebBPGJF7ux0_JYXI2Dw";
    static String FOLDER_ID = "b1gp970jvso2v3gtgbqt";
    static String API_URL = "https://tts.api.cloud.yandex.net/speech/v1/tts:synthesize";


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
        var conversationTable = ConversationTable.getTable();
        var buttonTextReplyTable = ButtonTextReplyTable.getTable();
        var buttonAudioReplyesHashMap = ButtonAudioReplyHashMap.getSet();

        if (message == null) {
            System.out.println("null message");
            return;
        }

        if (message.hasLocation()) {

            var location = message.getLocation();
            var lat = location.getLatitude();
            var lon = location.getLongitude();

            var parserResult =
                    new WeatherCordCommand().returnAnswerToLocation(lat.toString(), lon.toString());
            var commandResult = parserResult.stringOutput;

            var splitAnswer = commandResult.split(System.lineSeparator());
            var icon = parserResult.parserResult.icon;

            var item = new ResItem(true, parserResult.stringOutput, icon,
                    parserResult.recommendation, parserResult.parserResult);


            Results.TEXT = item.TextResult;
            Results.ICON = item.Icon;
            Results.PARSER_RESULT = parserResult.parserResult;


            sendPhoto(icon, message);
            String messageTextResult = String.join(System.lineSeparator(), splitAnswer);
            var textAnswer =
                    new ParserOutput(messageTextResult, parserResult.recommendation, parserResult.parserResult);


            sendMessageWithButtons(message, textAnswer);

        } else if (update.hasCallbackQuery()) {
            try {
                execute(new SendMessage().setText(
                                update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (buttonAudioReplyesHashMap.contains(message.getText())) {
            var textAnalog = message.getText().replace("Audio", "Text");
            if (buttonTextReplyTable.containsKey(textAnalog)) {
                var recommendation = (Recommendation) buttonTextReplyTable.get(textAnalog);
                var formulateRecommendation = recommendation.formOfRecommendation();
                try {
                    sendAudio(message, formulateRecommendation);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else
                System.out.println("NOT ENOUGH INFORMATION");

        } else if (conversationTable.containsKey(message.getText())) {
            var simpleCommand = (SimpleBotCommand) conversationTable.get(message.getText());
            sendMessage(message, simpleCommand.returnAnswer());
        } else if (buttonTextReplyTable.containsKey(message.getText())) {
            var recommendation = (Recommendation) buttonTextReplyTable.get(message.getText());

            var formulateRecommendation = recommendation.formOfRecommendation();
            sendMsgWithRecommendation(message, formulateRecommendation);
        } else if (message.hasText()) {
            var messageText = message.getText();
            sendMessageWithButtons(message, getAnswerToCommand(messageText, message));
        }
    }

    public ParserOutput getAnswerToCommand(String messageText, Message message) {
        String answer;

        var commandTable = CommandTable.getTable();

        if (commandTable.containsKey(messageText.split(" ")[0])) {
            var answerItem = CommandTable.getItem(commandTable, messageText);

            var parserResult = answerItem.parserResult;

            var recommendation = answerItem.Recommendation;

            Results.TEXT = answerItem.TextResult;
            Results.ICON = answerItem.Icon;
            Results.PARSER_RESULT = answerItem.parserResult;

            if (answerItem.Icon != null) {
                sendPhoto(answerItem.Icon, message);
            }
            answer = answerItem.TextResult;
            return new ParserOutput(answer, recommendation, parserResult);
        } else if (messageText.indexOf('/') != -1) {
            answer = "Я не знаю, что тебе ответить, ты ввел неправильную комманду";
            return new ParserOutput(answer);
        } else {
            answer = "Извини, я пока не умею веести свободный диалог((( Попробуй ввести комманду";
            return new ParserOutput(answer);
        }
    }

    private void sendAudio(Message message, String formulateRecommendation) throws UnsupportedEncodingException {
        var voiceToSend = new SendVoice();
        voiceToSend.setChatId(message.getChatId());
        ByteArrayInputStream audioRecommendation = new Converter().convertStringToAudio(formulateRecommendation,
                YANDEX_TOKEN, FOLDER_ID, API_URL);
        voiceToSend.setNewVoice("Answer", audioRecommendation);
        try {
            sendVoice(voiceToSend);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhoto(String icon, Message message) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(message.getChatId().toString());
        var photoURL = START_OF_PHOTO_URL + icon + END_OF_PHOTO_URL;
        sendPhotoRequest.setPhoto(photoURL);
        try {
            sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageWithButtons(Message message, ParserOutput answer) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(answer.stringOutput);

        setButtons(sendMessage);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Message message, String stringAnswer) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(stringAnswer);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsgWithRecommendation(Message message, String recommendationText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(recommendationText);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "weth_proj_bot";
    }

    @Override
    public String getBotToken() {
        Path path = Paths.get("C:\\Tokens\\Token.txt");
        String contents = null;
        try {
            contents = Files.readString(path, StandardCharsets.ISO_8859_1);
        } catch (IOException ignored) {
        }
        return contents;
    }

    @Override
    public void onClosing() {
        super.onClosing();
    }

    public synchronized void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();

        keyboard.add(addKeyboardRow("WeatherText"));
        keyboard.add(addKeyboardRow("ClothText"));
        keyboard.add(addKeyboardRow("WeatherAudio"));
        keyboard.add(addKeyboardRow("ClothAudio"));


        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    private KeyboardRow addKeyboardRow(String buttonText) {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton(buttonText));
        return keyboardRow;
    }


    public static class Results {
        public static String TEXT;
        public static String ICON;
        public static JsonParserResult PARSER_RESULT;
    }
}
