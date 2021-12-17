package bot;

import commands.JsonParserResult;

public class ResItem {
    public String TextResult;
    public String Icon;
    public String Recommendation;
    public JsonParserResult parserResult;

    public ResItem(boolean isFindIcon, String messageTextResult, String icon,
                   String recommendation, JsonParserResult _parserResult) {
        if (isFindIcon)
            this.Icon = icon;
        this.TextResult = messageTextResult;
        this.Recommendation = recommendation;
        this.parserResult = _parserResult;
    }
}
