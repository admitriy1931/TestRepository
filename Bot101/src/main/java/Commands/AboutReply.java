package commands;

public class AboutCommand implements DialogAnswer {
    @Override
    public String getContent() {
        return "Я - Бот для Telegram, написанный на java. " +
                "Комманда разработки: Агафонов Дмитрий, Сливный Артём";
    }
}
