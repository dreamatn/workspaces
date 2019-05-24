package com.hisun.BP;

public class BPCSTNRU {
    public int BR = 0;
    public String CCY = " ";
    public String BASE_TYP = " ";
    public int INTR_CNT = 0;
    public BPCSTNRU_INTR_TBL[] INTR_TBL = new BPCSTNRU_INTR_TBL[50];
    public BPCSTNRU() {
        for (int i=0;i<50;i++) INTR_TBL[i] = new BPCSTNRU_INTR_TBL();
    }
}
