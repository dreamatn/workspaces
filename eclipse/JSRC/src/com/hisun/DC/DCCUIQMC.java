package com.hisun.DC;

public class DCCUIQMC {
    public DCCUIQMC_INP_DATA INP_DATA = new DCCUIQMC_INP_DATA();
    public DCCUIQMC_OUT_DATA[] OUT_DATA = new DCCUIQMC_OUT_DATA[100];
    public DCCUIQMC() {
        for (int i=0;i<100;i++) OUT_DATA[i] = new DCCUIQMC_OUT_DATA();
    }
}
