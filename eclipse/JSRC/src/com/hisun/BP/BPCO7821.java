package com.hisun.BP;

public class BPCO7821 {
    public int TOTAL_NUM = 0;
    public int TOD_REC_NUM = 0;
    public String END_POS = " ";
    public char END_FLG = ' ';
    public BPCO7821_TIMES[] TIMES = new BPCO7821_TIMES[6];
    public BPCO7821() {
        for (int i=0;i<6;i++) TIMES[i] = new BPCO7821_TIMES();
    }
}
