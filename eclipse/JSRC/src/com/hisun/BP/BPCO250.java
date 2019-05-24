package com.hisun.BP;

public class BPCO250 {
    public int TOD_REC_NUM = 0;
    public String END_POS = " ";
    public char END_FLG = ' ';
    public BPCO250_DATA[] DATA = new BPCO250_DATA[100];
    public BPCO250() {
        for (int i=0;i<100;i++) DATA[i] = new BPCO250_DATA();
    }
}
