package com.hisun.BP;

public class BPCSINTI {
    public String OUTPUT_FMT = " ";
    public int DT = 0;
    public int BR = 0;
    public BPCSINTI_INTR_TBL[] INTR_TBL = new BPCSINTI_INTR_TBL[10];
    public BPCSINTI() {
        for (int i=0;i<10;i++) INTR_TBL[i] = new BPCSINTI_INTR_TBL();
    }
}
