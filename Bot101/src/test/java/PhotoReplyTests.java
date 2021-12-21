import imageReplyes.GeoMap;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhotoReplyTests {
    @Test
    void PhotoTest() throws IOException {
        var actual = new GeoMap().getReply("http://openweathermap.org/img/wn/13d@2x.png",
                61.38593673706055,55.15963363647461);
        File file = new File("C:\\bytes.txt");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        var list = new ArrayList<>(List.of(line.split(",")));
        var arrayList = new ArrayList<String>();
        for (var e:actual
             ) {
            String l = (String.valueOf(e));
            arrayList.add(l);
        }
        Collections.sort(list);
        Collections.sort(arrayList);
        assertEquals(list,arrayList);
    }
}
