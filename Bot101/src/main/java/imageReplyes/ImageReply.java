package imageReplyes;

import java.io.IOException;

public interface ImageReply {
    void getReply(String imageURL, Double lat, Double lon) throws IOException;
}
