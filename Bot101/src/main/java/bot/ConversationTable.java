package bot;

import commands.AboutCommand;
import commands.HelpCommand;
import commands.SimpleBotCommand;

import java.util.HashMap;

public class ConversationTable {
    private static final HashMap<String, SimpleBotCommand> Table = constructConversationTable();

    private static HashMap<String, SimpleBotCommand> constructConversationTable() {
        var conversationDic = new HashMap<String, SimpleBotCommand>();

        conversationDic.put("/help", new HelpCommand());
        conversationDic.put("/about", new AboutCommand());
        return conversationDic;
    }

    public static HashMap<String, SimpleBotCommand> getTable() {
        return Table;
    }
}
