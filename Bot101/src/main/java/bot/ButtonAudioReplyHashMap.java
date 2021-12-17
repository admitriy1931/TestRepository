package bot;

import advisor.ClothText;
import advisor.Recommendation;
import advisor.WeatherText;


import java.util.HashMap;
import java.util.HashSet;

public class ButtonAudioReplyHashMap {
    private static final HashSet Set = constructAudioSet();

    private static HashSet constructAudioSet() {
        var conversationSet = new HashSet<String>();

        conversationSet.add("WeatherAudio");
        conversationSet.add("ClothAudio");
        return conversationSet;
    }
    public static HashSet getSet() {
        return Set;
    }
}