package ru.netology.graphics;

import ru.netology.graphics.image.ColorShema;
import ru.netology.graphics.image.GraphicsConverter;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

public class Main {


    public static void main(String[] args) throws Exception {

        TextGraphicsConverter converter = new GraphicsConverter(); // Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        // Или то же, но с выводом на экран:
       /* String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
        converter.setMaxHeight(50);
        converter.setMaxWidth(50);
        converter.setMaxRatio(4);


        String imgTxt = converter.convert(url);
        System.out.println(imgTxt);*/


    }
}
