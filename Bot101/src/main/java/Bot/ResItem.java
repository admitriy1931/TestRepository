package Bot;

public class ResItem {
    public String Result;
    public String Icon;
    public ResItem(boolean isFindIcon, String messageTextResult, String icon) {
        if (isFindIcon)
            this.Icon = icon;
        this.Result = messageTextResult;
    }
}
