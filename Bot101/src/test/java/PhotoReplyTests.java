import imageReplyes.GeoMap;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhotoReplyTests {
    @Test
    void PhotoReplyTest() throws IOException {
        var actual = new GeoMap().getReply("http://openweathermap.org/img/wn/04d@2x.png",
                61.391170501708984,55.16817855834961);
        var arrayList = new ArrayList<String>();
        for (var e:actual
        ) {
            String stringLine = (String.valueOf(e));
            arrayList.add(stringLine);
        }
        String fileName = "src/test/bytesForTest";
        ArrayList list = (ArrayList) Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        assertEquals(list,arrayList);
    }
}
