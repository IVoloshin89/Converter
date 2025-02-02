package ru.netology.graphics.image;

public class ColorShema implements TextColorSchema {
    @Override
    public char convert(int color) {
        char symbol = '0';
        if (color >= 0 & color < 32) {
            symbol = '#';
        }
        if (color >= 32 & color < 64) {
            symbol = '$';
        }
        if (color >= 64 & color < 96) {
            symbol = '@';
        }
        if (color >= 96 & color < 128) {
            symbol = '%';
        }
        if (color >= 128 & color < 160) {
            symbol = '*';
        }
        if (color >= 160 & color < 192) {
            symbol = '+';
        }
        if (color >= 192 & color < 224) {
            symbol = '-';
        }
        if (color >= 224 & color < 256) {
            symbol = '"';
        }
        return symbol;
    }
}