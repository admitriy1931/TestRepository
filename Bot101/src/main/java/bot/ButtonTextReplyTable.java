package bot;

import advisor.ClothText;
import advisor.Recommendation;
import advisor.WeatherText;


import java.util.HashMap;

public class ButtonTextReplyTable {
    private static final HashMap<String, Recommendation> Table = constructConversationTable();

    private static HashMap<String, Recommendation> constructConversationTable() {
        var conversationDic = new HashMap<String, Recommendation>();

        conversationDic.put("WeatherText", new WeatherText());
        conversationDic.put("ClothText", new ClothText());
        return conversationDic;
    }

    public static HashMap<String, Recommendation> getTable() {
        return Table;
    }
}
