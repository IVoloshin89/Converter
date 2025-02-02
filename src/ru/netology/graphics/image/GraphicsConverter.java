package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class GraphicsConverter implements TextGraphicsConverter {
    protected int height;
    protected int width;
    protected double maxRatio;

    public TextColorSchema schema;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));

        int realWidth = img.getWidth();
        //System.out.println(realWidth);
        int realHeight = img.getHeight();
        //System.out.println(realHeight);
        double ratio = realWidth / realHeight;

        //проверка на максимальное разрешение изображения
        if (ratio > maxRatio) {
            throw new BadImageSizeException(ratio, maxRatio);
        }
        double kfWidtg = (double) realWidth / width;
        //System.out.println("kfHight = " + kfWidtg);
        double kfWidtg2;
        if (kfWidtg < 1) {
            kfWidtg2 = 1 / kfWidtg;
        } else {
            kfWidtg2 = kfWidtg;
        }
        double kfHight2;
        double kfHight = (double) realHeight / height;
        if (kfHight < 1) {
            kfHight2 = 1 / kfWidtg;
        } else {
            kfHight2 = kfWidtg;
        }
        double koef = 1;
        if (kfHight2 >= kfWidtg2) {
            koef = kfHight;
        } else {
            koef = kfWidtg;
        }
        System.out.println("koef = " + koef);
        int newWidth = (int) (realWidth * koef);
        System.out.println(newWidth);

        int newHeight = (int) (realHeight * koef);
        System.out.println(newHeight);
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH); //изменение размеров картинки

        //преобразовываем картинку в ЧБ
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        // Попросим у этой картинки инструмент для рисования на ней:
        Graphics2D graphics = bwImg.createGraphics();
        // А этому инструменту скажем, чтобы он скопировал содержимое из нашей суженной картинки:
        graphics.drawImage(scaledImage, 0, 0, null);
        //Проход по каждому пикселю картинки
        WritableRaster bwRaster = bwImg.getRaster();

        int[] colorArray = new int[3];
        StringBuilder arrayString = new StringBuilder();
        TextColorSchema schema1 = new ColorShema();

        for (int i = 1; i < newHeight; i++) {
            for (int j = 1; j < newWidth; j++) {
                int color = bwRaster.getPixel(j, i, colorArray)[0];
                char c  = schema1.convert(color);
                arrayString.append(c);
                arrayString.append(c);
            }
            arrayString.append("\n");
        }

        String result = arrayString.toString();

        return result;
    }

    @Override
    public void setMaxWidth(int width) {
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;

    }
}
