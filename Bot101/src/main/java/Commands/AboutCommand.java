package commands;

public class AboutCommand implements SimpleBotCommand {
    @Override
    public String returnAnswer(String string) {
        return "Я - Бот для Telegram, написанный на java. " +
                "Комманда разработки: Агафонов Дмитрий, Сливный Артём";
    }
}
