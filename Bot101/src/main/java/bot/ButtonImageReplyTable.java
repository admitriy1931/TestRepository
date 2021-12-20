package bot;
import imageReplyes.GeoMap;
import imageReplyes.ImageReply;

import java.util.HashMap;

public class ButtonImageReplyTable {
    private static final HashMap<String, ImageReply> Table = constructConversationTable();

    private static HashMap<String, ImageReply> constructConversationTable() {
        var imageRepliesDic = new HashMap<String, ImageReply>();

        imageRepliesDic.put("GeoData", new GeoMap());
        return imageRepliesDic;
    }

    public static HashMap<String, ImageReply> getTable() {
        return Table;
    }
}
