package bot;

import commands.HelpReply;
import commands.AboutReply;
import commands.DialogAnswer;

import java.util.HashMap;

public class ConversationTable {
    private static final HashMap Table = constructConversationTable();

    private static HashMap constructConversationTable() {
        var conversationDic = new HashMap<String, DialogAnswer>();

        conversationDic.put("/help", new HelpReply());
        conversationDic.put("/about", new AboutReply());
        return conversationDic;
    }

    public static HashMap getTable() {
        return Table;
    }
}
