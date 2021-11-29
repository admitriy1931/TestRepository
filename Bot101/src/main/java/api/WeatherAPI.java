package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WeatherAPI {
    public static String getWeatherString(String adress) {

        try {
            var urlAdress = new URL(adress);
            HttpURLConnection httpConnection = (HttpURLConnection) urlAdress.openConnection();
            var bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            var buffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null)
                buffer.append(line).append("\n");
            bufferedReader.close();
            return buffer.toString();
        } catch (Exception e) {
            System.out.println("ERROR");
            return "Нет такого города";
        }
    }

    public static String getContent(String city) {
        var address = "http://api.openweathermap.org/data/2.5/weather?q=" + city +
                "&appid=0b906e32b1ec09ac418edf018294b72a&units=metric";
        return getWeatherString(address);
    }

    public static String getContent(String inputLat, String inputLon) {
        var address = "http://api.openweathermap.org/data/2.5/onecall?lat=" + inputLat + "&lon=" +
                inputLon + "&appid=0b906e32b1ec09ac418edf018294b72a&units=metric";
        return getWeatherString(address);

    }

    public static String getContentId(String idOfCity) {
        var address = "http://api.openweathermap.org/data/2.5/weather?id=" + idOfCity +
                "&appid=ece85b2e3f0fb126a238a176a7fe3925&units=metric";
        return getWeatherString(address);
    }

    public static String getContentInd(String ind, String countryCode) {
        var address = "http://api.openweathermap.org/data/2.5/weather?zip=" + ind + "," +
                countryCode + "&appid=0b906e32b1ec09ac418edf018294b72a&units=metric";
        return getWeatherString(address);
    }
}
