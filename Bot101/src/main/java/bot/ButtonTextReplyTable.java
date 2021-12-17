package bot;

import advisor.ClothText;
import advisor.Recommendation;
import advisor.WeatherText;


import java.util.HashMap;

public class ButtonReplyTable {
    private static final HashMap Table = constructConversationTable();

    private static HashMap constructConversationTable() {
        var conversationDic = new HashMap<String, Recommendation>();

        conversationDic.put("WeatherText", new WeatherText());
        conversationDic.put("ClothText", new ClothText());
        return conversationDic;
    }

    public static HashMap getTable() {
        return Table;
    }
}
