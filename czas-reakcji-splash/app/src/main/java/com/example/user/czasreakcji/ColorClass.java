package com.example.user.czasreakcji;

/**
 * Created by user on 14.12.2018.
 */

public class ColorClass {
    private String colorName;
    private int colorValueR;
    private int colorValueG;
    private int colorValueB;

    public ColorClass(String colorName, int colorValueR, int colorValueG, int colorValueB) {
        this.colorName = colorName;
        this.colorValueR = colorValueR;
        this.colorValueG = colorValueG;
        this.colorValueB = colorValueB;
    }

    public String getColorName() {
        return colorName;
    }

    public int getColorValueR() {
        return colorValueR;
    }

    public int getColorValueG() {
        return colorValueG;
    }

    public int getColorValueB() {
        return colorValueB;
    }

    public String toString(){
        return colorValueR + " " + colorValueG + " " + colorValueB;
    }
}
