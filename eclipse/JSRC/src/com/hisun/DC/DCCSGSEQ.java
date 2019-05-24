package com.hisun.DC;

public class DCCSGSEQ {
    public DCCSGSEQ_INP_DATA INP_DATA = new DCCSGSEQ_INP_DATA();
    public DCCSGSEQ_OUT_DATA[] OUT_DATA = new DCCSGSEQ_OUT_DATA[10];
    public DCCSGSEQ_RC RC = new DCCSGSEQ_RC();
    public DCCSGSEQ() {
        for (int i=0;i<10;i++) OUT_DATA[i] = new DCCSGSEQ_OUT_DATA();
    }
}
