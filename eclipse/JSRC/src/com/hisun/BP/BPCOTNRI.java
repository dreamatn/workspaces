package com.hisun.BP;

public class BPCOTNRI {
    public int DT = 0;
    public int BR = 0;
    public String CCY = " ";
    public String BASE_TYP = " ";
    public BPCOTNRI_TENOR_TBL[] TENOR_TBL = new BPCOTNRI_TENOR_TBL[50];
    public BPCOTNRI() {
        for (int i=0;i<50;i++) TENOR_TBL[i] = new BPCOTNRI_TENOR_TBL();
    }
}
