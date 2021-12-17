package bot;

import advisor.ClothText;
import advisor.Recommendation;
import advisor.WeatherText;


import java.util.HashMap;

public class ButtonAudioReplyTable {
    private static final HashMap Table = constructConversationTable();

    private static HashMap constructConversationTable() {
        var conversationDic = new HashMap<String, Recommendation>();

        conversationDic.put("Audio", new WeatherText());
        return conversationDic;
    }
    public static HashMap getTable() {
        return Table;
    }
}