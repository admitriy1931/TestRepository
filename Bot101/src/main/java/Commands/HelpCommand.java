package commands;

public class HelpCommand implements SimpleBotCommand {

    @Override
    public String returnAnswer(String input) {
        return "Привет, я робот! Я умею рассказать о себе по комманде /about, " +
                "а еще могу вернуть погоду, для этого введи /weather" +
                " пожалуйста, указывайте город английскими буквами" +
                "Ну а если тебе нужен быстрый результат, скинь свою геоокацию в чат со мной," +
                "я выведу погоду по твоему местоположению)";
    }
}
