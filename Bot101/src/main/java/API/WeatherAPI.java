package API;

import org.telegram.telegrambots.generics.LongPollingBot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class WeatherAPI {
    public static  String GetWeatherString(String adress){

        try {
            var urlAdress = new URL(adress);
            HttpURLConnection httpConnection = (HttpURLConnection) urlAdress.openConnection();
            var bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            var buffer = new StringBuffer();
            String line;

            while ((line = bufferedReader.readLine()) != null)
                buffer.append(line + "\n");
            bufferedReader.close();
            return buffer.toString();
        } catch (Exception e) {
            System.out.println("ERROR");
            return "Нет такого города";
        }

    }
    public static String GetContent(String city) {
        var getUserCity = city;
        var adress = "http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=0b906e32b1ec09ac418edf018294b72a&units=metric";
        return GetWeatherString(adress);
    }

    public static String GetContent(String inputLat, String inputLon) {
        var lat = inputLat;
        var lon = inputLon;
        var adress = "http://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&appid=0b906e32b1ec09ac418edf018294b72a&units=metric";
        return GetWeatherString(adress);

    }
    public static String GetContentLoc(Double inputLat, Double inputLon) {
        var lat = inputLat;
        var lon = inputLon;
        var adress = "http://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&appid=0b906e32b1ec09ac418edf018294b72a&units=metric";
        return GetWeatherString(adress);

    }
    public static String GetContentId(String idOfCity) {
        var id = idOfCity;
        var adress = "http://api.openweathermap.org/data/2.5/weather?id=" + id + "&appid=ece85b2e3f0fb126a238a176a7fe3925";
                                                                                         //ece85b2e3f0fb126a238a176a7fe3925
        return GetWeatherString(adress);
    }
    public static String GetContentInd(String ind, String countryCode){

        var index = ind;
        var country = countryCode;
        var adress = "http://api.openweathermap.org/data/2.5/weather?zip=" + index + "," + country + "&appid=0b906e32b1ec09ac418edf018294b72a&units=metric";
        return GetWeatherString(adress);
    }
}
