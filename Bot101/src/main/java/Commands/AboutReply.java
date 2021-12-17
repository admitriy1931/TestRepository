package commands;

public class AboutReply implements DialogAnswer {
    @Override
    public String getContent() {
        return "Я - Бот для Telegram, написанный на java. " +
                "Комманда разработки: Агафонов Дмитрий, Сливный Артём";
    }
}
