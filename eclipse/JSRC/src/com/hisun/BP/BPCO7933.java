package com.hisun.BP;

public class BPCO7933 {
    public int TOTAL_NUM = 0;
    public int TOD_REC_NUM = 0;
    public String END_POS = " ";
    public char END_FLG = ' ';
    public BPCO7993_TIMES[] TIMES = new BPCO7993_TIMES[20];
    public BPCO7933() {
        for (int i=0;i<20;i++) TIMES[i] = new BPCO7993_TIMES();
    }
}
