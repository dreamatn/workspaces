package com.hisun.AI;

public class AICF380 {
    public int TOD_REC_NUM = 0;
    public String END_POS = " ";
    public char END_FLG = ' ';
    public AICF380_DATA[] DATA = new AICF380_DATA[30];
    public AICF380() {
        for (int i=0;i<30;i++) DATA[i] = new AICF380_DATA();
    }
}
