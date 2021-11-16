package bot;

public class ResItem {
    public String Result;
    public String Icon;
    public String Recommendation;

    public ResItem(boolean isFindIcon, String messageTextResult, String icon, String recommendation) {
        if (isFindIcon)
            this.Icon = icon;
        this.Result = messageTextResult;
        this.Recommendation = recommendation;
    }
}
