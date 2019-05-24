package com.hisun.BP;

public class BPCOTNRU {
    public int DT = 0;
    public int BR = 0;
    public String CCY = " ";
    public String BASE_TYP = " ";
    public String BASE_NAME = " ";
    public char FILLER6 = 0X02;
    public int CNT = 0;
    public BPCOTNRU_TENOR_TBL[] TENOR_TBL = new BPCOTNRU_TENOR_TBL[50];
    public BPCOTNRU() {
        for (int i=0;i<50;i++) TENOR_TBL[i] = new BPCOTNRU_TENOR_TBL();
    }
}
