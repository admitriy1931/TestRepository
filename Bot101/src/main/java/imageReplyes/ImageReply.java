package imageReplyes;

import java.io.IOException;

public interface ImageReply {
    byte[] getReply(String imageURL, Double lat, Double lon) throws IOException;
}
