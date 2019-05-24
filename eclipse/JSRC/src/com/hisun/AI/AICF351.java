package com.hisun.AI;

public class AICF351 {
    public int TOD_REC_NUM = 0;
    public String END_POS = " ";
    public char END_FLG = ' ';
    public AICF351_DATA[] DATA = new AICF351_DATA[10];
    public AICF351() {
        for (int i=0;i<10;i++) DATA[i] = new AICF351_DATA();
    }
}
