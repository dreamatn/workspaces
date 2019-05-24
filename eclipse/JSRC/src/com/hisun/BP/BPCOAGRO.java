package com.hisun.BP;

public class BPCOAGRO {
    public BPCOAGRO_KEY KEY = new BPCOAGRO_KEY();
    public BPCOAGRO_DATE DATE = new BPCOAGRO_DATE();
    public BPCOAGRO_VAL VAL = new BPCOAGRO_VAL();
    public BPCOAGRO_FEE_INFOR[] FEE_INFOR = new BPCOAGRO_FEE_INFOR[50];
    public short ARRAY_CNT = 0;
    public BPCOAGRO() {
        for (int i=0;i<50;i++) FEE_INFOR[i] = new BPCOAGRO_FEE_INFOR();
    }
}
