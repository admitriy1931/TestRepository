import commands.AboutCommand;
import commands.HelpCommand;
import org.junit.jupiter.api.Test;
import bot.Bot;
import org.telegram.telegrambots.api.objects.Message;
import static org.junit.jupiter.api.Assertions.*;

class RightSimpleAnswerTest {
    @Test
    void getRightSimpleAnswer() {
        var answer1 = "Привет, я робот! Я умею рассказать о себе по комманде /about, " +
                "а еще могу вернуть погоду, для этого введи /weather" +
                " пожалуйста, указывайте город английскими буквами" +
                "Ну а если тебе нужен быстрый результат, скинь свою геоокацию в чат со мной," +
                "я выведу погоду по твоему местоположению)";
        var answer2 = "Я - Бот для Telegram, написанный на java. " +
                "Комманда разработки: Агафонов Дмитрий, Сливный Артём";
        var answer3 = "Я не знаю, что тебе ответить, ты ввел неправильную комманду";
        var testBot = new Bot();
        assertEquals(new HelpCommand().returnAnswer(),answer1);
        assertEquals(new AboutCommand().returnAnswer(), answer2);
        assertEquals(testBot.getAnswerToCommand("/asajla",new Message()).stringOutput, answer3);
        assertEquals(testBot.getAnswerToCommand("/kasjsbaaa",new Message()).stringOutput, answer3);
    }
    @Test
    void getBotUsername() {
        assertEquals(new Bot().getBotUsername(), "weth_proj_bot");
    }
}