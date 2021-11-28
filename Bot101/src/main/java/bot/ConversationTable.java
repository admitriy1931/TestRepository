package bot;

import commands.AboutCommand;
import commands.HelpCommand;
import commands.SimpleBotCommand;

import java.util.HashMap;

public class ConversationTable {
    private static final HashMap Table = constructConversationTable();

    private static HashMap constructConversationTable() {
        var conversationDic = new HashMap<String, SimpleBotCommand>();

        conversationDic.put("/help", new HelpCommand());
        conversationDic.put("/about", new AboutCommand());
        return conversationDic;
    }

    public static HashMap getTable() {
        return Table;
    }
}
