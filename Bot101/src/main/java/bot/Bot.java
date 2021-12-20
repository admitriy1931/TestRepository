package bot;

import advisor.Recommendation;
import commands.JsonParserResult;
import commands.ParserOutput;
import commands.SimpleBotCommand;
import commands.WeatherCordCommand;
import imageReplyes.ImageReply;
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;


public class Bot extends TelegramLongPollingBot {
    public Bot() {
    }

    static String START_OF_PHOTOURL =
            "http://openweathermap.org/img/wn/";
    static String END_OF_PHOTOURL = "@2x.png";
    static String FOLDER_ID = "b1gp970jvso2v3gtgbqt";
    static YandexToken YANDEX_TOKEN = new YandexToken();
    static String API_URL = "https://tts.api.cloud.yandex.net/speech/v1/tts:synthesize";


    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        Results.UsersInformation = new HashMap<>();
    }


    @Override
    public void onUpdateReceived(Update update) {

        var message = update.getMessage();
        var chatId = message.getChatId();

        if (!Results.UsersInformation.containsKey(chatId))
            Results.UsersInformation.put(chatId, new UserData());

        var conversationTable = ConversationTable.getTable();
        var buttonTextReplyTable = ButtonTextReplyTable.getTable();
        var buttonAudioReplyesHashSet = ButtonAudioReplyHashMap.getSet();
        var buttonImageReplyTable = ButtonImageReplyTable.getTable();

        if (message.hasLocation()) {
            var location = message.getLocation();
            var lat = location.getLatitude();
            var lon = location.getLongitude();


            Results.UsersInformation.get(chatId).LAT = lat;
            Results.UsersInformation.get(chatId).LON = lon;



            var parserResult =
                    new WeatherCordCommand().returnAnswerToLocation(lat.toString(), lon.toString());
            var commandResult = parserResult.stringOutput;

            var splitAnswer = commandResult.split(System.lineSeparator());
            var icon = parserResult.parserResult.icon;
            var item = new ResItem(true, parserResult.stringOutput, icon,
                    parserResult.recommendation, parserResult.parserResult);


            Results.UsersInformation.get(chatId).TEXT = item.TextResult;
            Results.UsersInformation.get(chatId).ICON = item.Icon;
            Results.UsersInformation.get(chatId).PARSER_RESULT = parserResult.parserResult;

            sendPhoto(icon, message);
            String messageTextResult = String.join(System.lineSeparator(), splitAnswer);
            var textAnswer =
                    new ParserOutput(messageTextResult, parserResult.recommendation, parserResult.parserResult);


            sendMsg(message, textAnswer);

        } else if (update.hasCallbackQuery()) {
            try {
                execute(new SendMessage().setText(
                                update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else if (buttonImageReplyTable.containsKey(message.getText())){
            var iconURL = START_OF_PHOTOURL + Results.UsersInformation.get(chatId).ICON + END_OF_PHOTOURL;
            sendPhotoAsReply(message, iconURL, buttonImageReplyTable.get(message.getText()), chatId);


        }else if (buttonAudioReplyesHashSet.contains(message.getText())) {
            var textAnalog = message.getText().replace("Audio", "Text");
            if (buttonTextReplyTable.containsKey(textAnalog)) {
                var recommendation = (Recommendation) buttonTextReplyTable.get(textAnalog);
                var formulateRecommendation = recommendation.formOfRecommendation(chatId);
                try {
                    sendAudio(message, formulateRecommendation);
                } catch (InterruptedException | IOException | TimeoutException e) {
                    e.printStackTrace();
                }
            } else
                System.out.println("NOT ENOUGH INFORMATION");

        } else if (conversationTable.containsKey(message.getText())) {
            var simpleCommand = (SimpleBotCommand) conversationTable.get(message.getText());
            sendMsg(message, simpleCommand.returnAnswer());
        } else if (buttonTextReplyTable.containsKey(message.getText())) {
            var recommendation = (Recommendation) buttonTextReplyTable.get(message.getText());

            var formulateRecommendation = recommendation.formOfRecommendation(chatId);
            sendMsgWithRecommendation(message, formulateRecommendation);
        } else if (message.hasText()) {
            var messageText = message.getText();
            sendMsg(message, getAnswerToCommand(messageText, message, chatId));
        }
    }

    private void TryGetCoordinates(String[] splitText, Long chatId) {
        if (Objects.equals(splitText[0], "/weatherCord") && splitText.length == 3)
        {
            Results.UsersInformation.get(chatId).LAT = Double.parseDouble(splitText[1]);
            Results.UsersInformation.get(chatId).LON = Double.parseDouble(splitText[2]);
        }
    }

    public ParserOutput getAnswerToCommand(String messageText, Message message, Long chatId) {
        String answer;

        var commandTable = CommandTable.getTable();
        var splitText = messageText.split(" ");
        if (commandTable.containsKey(splitText[0])) {

            TryGetCoordinates(splitText, chatId);

            var answerItem = CommandTable.getItem(commandTable, messageText);

            var parserResult = answerItem.parserResult;

            var recommendation = answerItem.Recommendation;


            Results.UsersInformation.get(chatId).TEXT = answerItem.TextResult;
            Results.UsersInformation.get(chatId).ICON = answerItem.Icon;
            Results.UsersInformation.get(chatId).PARSER_RESULT = answerItem.parserResult;

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

    public void sendAudio(Message message, String formulateRecommendation) throws IOException, InterruptedException, TimeoutException {
        var sendVoice = new SendVoice();
        YANDEX_TOKEN.UpdateYandexToken();

        sendVoice.setChatId(message.getChatId());
        ByteArrayInputStream byteStream = Converter.convertStringToAudio(formulateRecommendation,
                YANDEX_TOKEN.GetToken(), FOLDER_ID, API_URL);

        sendVoice.setNewVoice("WeatherAudio", byteStream);
        try {
            sendVoice(sendVoice);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhoto(String icon, Message message) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(message.getChatId().toString());

        var photoURL = START_OF_PHOTOURL + icon + END_OF_PHOTOURL;

        sendPhotoRequest.setPhoto(photoURL);
        try {
            sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhotoAsReply(Message message, String iconURL, ImageReply imageReply, Long chatId){
        try {
            var replyImageBytes = imageReply
                    .getReply(iconURL,Results.UsersInformation.get(chatId).LON, Results.UsersInformation.get(chatId).LAT);
            SendPhoto sender = new SendPhoto();
            sender.setChatId(message.getChatId().toString());
            sender.setNewPhoto("ReplyPhoto", new ByteArrayInputStream(replyImageBytes));
            sendPhoto(sender);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, ParserOutput answer) {

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

    private void sendMsg(Message message, String stringAnswer) {
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

        keyboard.add(addSingleRow("WeatherText"));
        keyboard.add(addSingleRow("ClothText"));
        keyboard.add(addSingleRow("WeatherAudio"));
        keyboard.add(addSingleRow("ClothAudio"));
        keyboard.add(addSingleRow("GeoData"));
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    private KeyboardRow addSingleRow(String buttonText) {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton(buttonText));
        return keyboardRow;
    }


    public static class Results {
        public static HashMap<Long, UserData> UsersInformation;
    }

    public static class UserData{
        public String TEXT;
        public String ICON;
        public JsonParserResult PARSER_RESULT;

        public double LAT;
        public double LON;
    }

}
