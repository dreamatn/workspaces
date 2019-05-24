package com.hisun.LN;

public class LNCICNQ_COMM_DATA {
    public String INQ_CODE = " ";
    public String CONTRACT_NO = " ";
    public int SUB_CTA_NO = 0;
    public LNCICNQ_CNTR[] CNTR = new LNCICNQ_CNTR[10];
    public LNCICNQ_COMM_DATA() {
        for (int i=0;i<10;i++) CNTR[i] = new LNCICNQ_CNTR();
    }
}
