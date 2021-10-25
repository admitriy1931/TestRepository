package Commands;

public class AboutCommand implements BotCommand {
    @Override
    public String returnAnswer(String string) {
        return "Я - Бот для Telegram, написанный на java. " +
                "Комманда разработки: Агафонов Дмитрий, Сливный Артём";
    }
}
