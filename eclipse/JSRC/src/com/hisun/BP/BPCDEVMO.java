package com.hisun.BP;

public class BPCDEVMO {
    public char TYPE = ' ';
    public int BR = 0;
    public String CCY = " ";
    public String BASE_TYP = " ";
    public String TENOR = " ";
    public char FMT = ' ';
    public BPCDEVMO_DATA[] DATA = new BPCDEVMO_DATA[10];
    public String BASE_NAME = " ";
    public char FILLER12 = 0X02;
    public String TENOR_NAME = " ";
    public char FILLER14 = 0X02;
    public BPCDEVMO() {
        for (int i=0;i<10;i++) DATA[i] = new BPCDEVMO_DATA();
    }
}
