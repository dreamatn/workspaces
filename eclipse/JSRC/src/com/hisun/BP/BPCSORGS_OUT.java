package com.hisun.BP;

public class BPCSORGS_OUT {
    public short PAGE = 0;
    public int NUM = 0;
    public short PAGE = 0;
    public char PAGE = ' ';
    public short PAGE_ROW = 0;
    public BPCSORGS_OUT_DATA[] OUT_DATA = new BPCSORGS_OUT_DATA[100];
    public BPCSORGS_OUT() {
        for (int i=0;i<100;i++) OUT_DATA[i] = new BPCSORGS_OUT_DATA();
    }
}
