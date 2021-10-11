import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;

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

    @Override
    public void onUpdatesReceived(List<Update> updates)
    {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername()
    {
        return "BOT_NAME";
    }

    @Override
    public String getBotToken()
    {
        return "BOT_TOKEN";
    }

    @Override
    public void onClosing()
    {
        super.onClosing();
    }
}
