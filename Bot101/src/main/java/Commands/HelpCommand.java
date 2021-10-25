package Commands;

public class HelpCommand implements BotCommand {

    @Override
    public String returnAnswer(String input) {
        return "Привет, я робот! Я умею рассказать о себе по комманде /about, " +
                "а еще могу вернуть погоду, для этого введи /weather" +
                " пожалуйста, указывайте город английскими буквами";
    }
}