package com.hisun.BP;

public class BPCO153 {
    public short COUNT = 0;
    public BPCO153_DATA[] DATA = new BPCO153_DATA[10];
    public char BV_FUNC = ' ';
    public String PB_NO = " ";
    public String REC_TLR = " ";
    public BPCO153() {
        for (int i=0;i<10;i++) DATA[i] = new BPCO153_DATA();
    }
}
