package com.hisun.BP;

public class BPCO155 {
    public BPCO155_BV_DATA[] BV_DATA = new BPCO155_BV_DATA[10];
    public String RCV_TLR = " ";
    public char MOV_FLG = ' ';
    public BPCO155() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPCO155_BV_DATA();
    }
}
