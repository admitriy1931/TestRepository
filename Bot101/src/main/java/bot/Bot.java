package bot;

import advisor.Recommendation;
import commands.JsonParserResult;
import commands.ParserOutput;
import commands.SimpleBotCommand;
import commands.WeatherCordCommand;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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

import javax.sound.sampled.AudioFormat;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;




public class Bot extends TelegramLongPollingBot {
    public Bot() {
    }

    static String START_OF_PHOTOURL =
            "http://openweathermap.org/img/wn/";
    static String END_OF_PHOTOURL = "@2x.png";


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

        if (message != null && message.hasLocation()) {

            var location = message.getLocation();
            var lat = location.getLatitude();
            var lon = location.getLongitude();

            var parserResult =
                    new WeatherCordCommand().returnAnswerToLocation(lat.toString(), lon.toString());
            var commandResult = parserResult.stringOutput;

            var splitAnswer = commandResult.split(System.lineSeparator());
            var icon = splitAnswer[splitAnswer.length - 1];
            var item = new ResItem(true, parserResult.stringOutput, icon,
                    parserResult.recommendation, parserResult.parserResult);


            Results.TEXT = item.TextResult;
            Results.ICON = item.Icon;
            Results.PARSER_RESULT = parserResult.parserResult;


            var isFindIcon = icon.length() == 3;

            if (isFindIcon) {
                splitAnswer = Arrays.copyOf(splitAnswer, splitAnswer.length - 2);
                SendPhoto(icon, message);
            }
            String messageTextResult = String.join(System.lineSeparator(), splitAnswer);
            var textAnswer =
                    new ParserOutput(messageTextResult, parserResult.recommendation, parserResult.parserResult);


            sendMsg(message, textAnswer, update);

        } else if (update.hasCallbackQuery()) {
            try {
                execute(new SendMessage().setText(
                                update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        //}else if (message.getText().equals("Audio")){
            //sendAudio(message);

        } else if (buttonAudioReplyesHashMap.contains(message.getText())) {
            var textAnalog = message.getText().replace("Audio", "Text");
            if (buttonTextReplyTable.containsKey(textAnalog)){
                var recommendation = (Recommendation) buttonTextReplyTable.get(textAnalog);
                var formulateRecommendation = recommendation.formOfRecommendation();
                try {
                    sendAudio(message, formulateRecommendation);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            //sendAudio(message);
            else
                System.out.println("NOT ENOUGH INFORMATION");

        } else if (conversationTable.containsKey(message.getText())) {
            var simpleCommand = (SimpleBotCommand) conversationTable.get(message.getText());
            sendMsg(message, simpleCommand.returnAnswer());
        } else if (buttonTextReplyTable.containsKey(message.getText())) {
            //System.out.println(buttonTextReplyTable.get(message.getText()));
            var recommendation = (Recommendation) buttonTextReplyTable.get(message.getText());

            var formulateRecommendation = recommendation.formOfRecommendation();
            sendMsgWithRecommendation(message, formulateRecommendation);
        } else if (message != null && message.hasText()) {
            var messageText = message.getText();
            sendMsg(message, getAnswerToCommand(messageText, message), update);
        }
    }






    public ParserOutput getAnswerToCommand(String messageText, Message message)//Можем тестить этот метод
    {
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
                SendPhoto(answerItem.Icon, message);
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
    public ByteArrayInputStream Tst(String recomendations) throws UnsupportedEncodingException {

        String iamToken = "t1.9euelZqcmZqNl4qbjZ7GmZSOisnLxu3rnpWayo6Lis-Lns6UnpuLzMrLkYnl8_dGQQly-e8_HXdh_t3z9wZwBnL57z8dd2H-.gZH0pLJ3vVm9H4Jji9JPsGgbQeiJlUE2Vf5HQXVGjGM6fWCz2KYU62ZYBQwFQyYkLOkXiemX__5DGHxewwZ7BA";
        String folderId = "b1gp970jvso2v3gtgbqt";
        String url = "https://tts.api.cloud.yandex.net/speech/v1/tts:synthesize";
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Authorization", "Bearer " + iamToken);
        List<NameValuePair> params = new ArrayList(3);
        params.add(new BasicNameValuePair("text", recomendations));
        params.add(new BasicNameValuePair("lang", "ru-RU"));
        params.add(new BasicNameValuePair("folderId", folderId));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        try {
            HttpResponse response = httpClient.execute(httpPost);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            response.getEntity().writeTo(byteStream);
            byte[] bytes = byteStream.toByteArray();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            return inputStream;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }

    public void sendAudio(Message message, String formulateRecommendation) throws UnsupportedEncodingException {
        var sendVoice = new SendVoice();
        sendVoice.setChatId(message.getChatId());
        ByteArrayInputStream byteStream = Tst(formulateRecommendation);
        sendVoice.setNewVoice("WeatherAudio",byteStream);
        try {
            sendVoice(sendVoice);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void SendPhoto(String icon, Message message) {
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

    private void sendMsg(Message message, ParserOutput answer, Update update) {

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
        } catch (IOException ex) {
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

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("WeatherText"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("ClothText"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("WeatherAudio"));

        KeyboardRow keyboardFouthRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("ClothAudio"));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        keyboard.add(keyboardFouthRow);


        replyKeyboardMarkup.setKeyboard(keyboard);
    }


    public static class Results {
        public static String TEXT;
        public static String ICON;
        public static JsonParserResult PARSER_RESULT;
    }

}
