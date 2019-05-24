package com.hisun.BP;

public class BPCHDEV {
    public char TYPE = ' ';
    public int BR = 0;
    public String CCY = " ";
    public String BASE_TYP = " ";
    public String TENOR = " ";
    public char FMT = ' ';
    public BPCHDEV_DATA[] DATA = new BPCHDEV_DATA[10];
    public BPCHDEV() {
        for (int i=0;i<10;i++) DATA[i] = new BPCHDEV_DATA();
    }
}
