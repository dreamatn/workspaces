package com.hisun.BP;

public class BPCO666 {
    public int BR = 0;
    public String TLR = " ";
    public char STS = ' ';
    public BPCO666_DATA[] DATA = new BPCO666_DATA[10];
    public char FUNC = ' ';
    public int DT = 0;
    public long NO = 0;
    public char TYPE = ' ';
    public BPCO666() {
        for (int i=0;i<10;i++) DATA[i] = new BPCO666_DATA();
    }
}
