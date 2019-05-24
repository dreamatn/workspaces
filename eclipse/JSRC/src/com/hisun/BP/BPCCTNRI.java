package com.hisun.BP;

public class BPCCTNRI {
    public BPCCTNRI_RC RC = new BPCCTNRI_RC();
    public int BR = 0;
    public String CCY = " ";
    public String BASE_TYP = " ";
    public int TENOR_CNT = 0;
    public BPCCTNRI_TENOR_TBL[] TENOR_TBL = new BPCCTNRI_TENOR_TBL[50];
    public BPCCTNRI() {
        for (int i=0;i<50;i++) TENOR_TBL[i] = new BPCCTNRI_TENOR_TBL();
    }
}
