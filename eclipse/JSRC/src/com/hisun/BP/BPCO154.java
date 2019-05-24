package com.hisun.BP;

public class BPCO154 {
    public String RCV_TLR = " ";
    public int CNT = 0;
    public BPCO154_DATA[] DATA = new BPCO154_DATA[10];
    public char BV_FUNC = ' ';
    public String PB_NO = " ";
    public BPCO154() {
        for (int i=0;i<10;i++) DATA[i] = new BPCO154_DATA();
    }
}
