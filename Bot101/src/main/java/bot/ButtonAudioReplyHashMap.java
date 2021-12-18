package bot;

import java.util.HashSet;

public class ButtonAudioReplyHashMap {
    private static final HashSet<String> Set = constructAudioSet();

    private static HashSet<String> constructAudioSet() {
        var conversationSet = new HashSet<String>();

        conversationSet.add("WeatherAudio");
        conversationSet.add("ClothAudio");
        return conversationSet;
    }

    public static HashSet<String> getSet() {
        return Set;
    }
}