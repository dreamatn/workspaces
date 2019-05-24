package com.hisun.BP;

public class BPCCVALI {
    public BPCCVALI_RC RC = new BPCCVALI_RC();
    public int BR = 0;
    public String CCY = " ";
    public String BASE_TYP = " ";
    public int TNR_CNT = 0;
    public BPCCVALI_TNR_TBL[] TNR_TBL = new BPCCVALI_TNR_TBL[50];
    public BPCCVALI() {
        for (int i=0;i<50;i++) TNR_TBL[i] = new BPCCVALI_TNR_TBL();
    }
}
