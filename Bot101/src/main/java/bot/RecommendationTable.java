package bot;

import advisor.ClothText;
import advisor.Recommendation;
import advisor.WeatherText;


import java.util.HashMap;

public class RecommendationTable {
    private static final HashMap Table = constructConversationTable();

    private static HashMap constructConversationTable() {
        var conversationDic = new HashMap<String, Recommendation>();

        conversationDic.put("Weather1", new WeatherText());
        conversationDic.put("Weather2", new ClothText());
        return conversationDic;
    }

    public static HashMap getTable() {
        return Table;
    }
}
