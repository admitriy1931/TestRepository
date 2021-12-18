import advisor.ClothChooser;
import commands.*;
import org.junit.jupiter.api.Test;
import bot.CommandTable;
import bot.Bot;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ClothChoosingTests {

    @Test
    void getClothForSoftSnowWeather() {
        var cloth = new ClothChooser(-9.0, "04", 4.0, "snow");
        assertEquals(cloth.body, "Пуховик");
        assertEquals(cloth.legs, "Утепленные джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Теплые ботинки");
    }

    @Test
    void getClothForStormSnowWeather() {
        var cloth = new ClothChooser(-4.62, "04", 19.0, "snow");
        assertEquals(cloth.body, "Парка");
        assertEquals(cloth.legs, "Утепленные джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Зимние сапоги");
    }

    @Test
    void getClothForWindlessRainyColdWeather() {
        var cloth = new ClothChooser(4.62, "04", 0.1, "Lite rain");
        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Сапоги");
    }

    @Test
    void getClothForWindlessRainyWarmWeather() {
        var cloth = new ClothChooser(17.62, "04", 0.0, "Heavy rain");
        assertEquals(cloth.body, "Худи");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Бейсболка");
        assertEquals(cloth.feets, "Кроссовки");
    }

    @Test
    void getClothForWindlessSoftWarmWeather() {
        var cloth = new ClothChooser(14.62, "04", 0.3, "clouds");
        assertEquals(cloth.body, "Поло");
        assertEquals(cloth.legs, "Шорты");
        assertEquals(cloth.head, "Легкая бейсболка");
        assertEquals(cloth.feets, "Мокасины");
    }

    @Test
    void getClothForWindlessSoftColdWeather() {
        var cloth = new ClothChooser(4.62, "04", 0.1, "clouds");
        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Походные ботинки");

    }

    @Test
    void getClothForWindyRainyWarmWeather() {
        var cloth = new ClothChooser(14.62, "04", 5.0, "rainy");
        assertEquals(cloth.body, "Ветровка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Бейсболка");
        assertEquals(cloth.feets, "Кеды");
    }

    @Test
    void getClothForWindySoftWarmWeather() {
        var cloth = new ClothChooser(14.67, "04", 3.0, "clouds");
        assertEquals(cloth.body, "Лонгслив");
        assertEquals(cloth.legs, "Брюки-карго");
        assertEquals(cloth.head, "Бейсболка");
        assertEquals(cloth.feets, "Кроссовки");
    }

    @Test
    void getClothForWindyRainyColdWeather() {
        var cloth = new ClothChooser(4.62, "04", 4.0, "clouds");
        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Ботинки");
    }

    @Test
    void getClothForWindySoftColdWeather() {
        var cloth = new ClothChooser(2.72, "04", 6.0, "clouds");
        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Ботинки");
    }
}