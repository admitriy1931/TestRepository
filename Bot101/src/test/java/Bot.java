import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
/*
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import taksebe.telegram.mentalCalculation.telegram.commands.operations.MinusCommand;
import ru.taksebe.telegram.mentalCalculation.telegram.commands.operations.PlusCommand;
import ru.taksebe.telegram.mentalCalculation.telegram.commands.operations.PlusMinusCommand;
import ru.taksebe.telegram.mentalCalculation.telegram.commands.service.HelpCommand;
import ru.taksebe.telegram.mentalCalculation.telegram.commands.service.SettingsCommand;
import ru.taksebe.telegram.mentalCalculation.telegram.commands.service.StartCommand;
import ru.taksebe.telegram.mentalCalculation.telegram.nonCommand.NonCommand;
import ru.taksebe.telegram.mentalCalculation.telegram.nonCommand.Settings;
*/

public class Bot extends TelegramLongPollingBot
{
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    
    public Bot(String bot_name, String bot_token)
    {
        super();
        this.BOT_NAME = bot_name;
        this.BOT_TOKEN = bot_token;
    }
    public Bot()
    {
        this.BOT_NAME = "";
        this.BOT_TOKEN = "";
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

/*
    @Override
    public void onUpdateReceived(Update update)
    {
        var answer = update.getMessage().getText();
        var chatId = update.getMessage().getChatId().toString();
        sendAnswer(answer, chatId);
    }

    private synchronized void sendAnswer(String answer, String chatId)
    {
        var sendMessage = new SendMessage();
        sendMessage.setText(answer);
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        try
        {
            execute(sendMessage);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
 */

    @Override
    public void onUpdateReceived(Update update)
    {
        //super.onUpdatesReceived(updates);
        var message = update.getMessage();
        if (message != null && message.hasText())
        {
            if (message.getText().equals("/help"))
                sendMsg(message, "Привет, я робот");
            else if (message.getText().equals("/about"))
                sendMsg(message, "Бот для telegram написанный на java. " +
                        "Комманда разработки: Агафонов Дмитрий, Сливный Артём");
            else
                sendMsg(message, "Я не знаю что ответить на это");
        }
    }

    private void sendMsg(Message message, String answer)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(answer);
        try
        {
            execute(sendMessage);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername()
    {
        return "att1000_bot";
    }

    @Override
    public String getBotToken()
    {
        return "1987090518:AAFbMTQ6R1HEfQh91RoPSCZ2Ee93HcRl5x8";
    }

    @Override
    public void onClosing()
    {
        super.onClosing();
    }
}
