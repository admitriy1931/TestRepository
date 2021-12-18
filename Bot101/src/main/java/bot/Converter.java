package bot;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static ByteArrayInputStream convertStringToAudio(String recommendations, String yandexToken, String folderId,
                                                     String apiUrl) throws UnsupportedEncodingException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(apiUrl);
        httppost.addHeader("Authorization", "Bearer " + yandexToken);
        List<NameValuePair> params = new ArrayList(3);
        params.add(new BasicNameValuePair("text", recommendations));
        params.add(new BasicNameValuePair("lang", "ru-RU"));
        params.add(new BasicNameValuePair("folderId", folderId));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        try {
            HttpResponse response = httpClient.execute(httppost);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            response.getEntity().writeTo(byteStream);
            byte[] bytes = byteStream.toByteArray();
            return new ByteArrayInputStream(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
