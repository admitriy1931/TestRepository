package commands;

public class AboutCommand implements SimpleBotCommand {
    @Override
    public String returnAnswer() {
        return "Я - Бот для Telegram, написанный на java. " +
                "Комманда разработки: Агафонов Дмитрий, Сливный Артём";
    }
}
