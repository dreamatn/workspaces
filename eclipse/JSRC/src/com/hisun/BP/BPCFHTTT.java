package com.hisun.BP;

public class BPCFHTTT {
    public short TOTAL_NUM = 0;
    public short TOD_REC_NUM = 0;
    public long END_POS = 0;
    public char END_FLG = ' ';
    public BPCFHTTT_TIMES[] TIMES = new BPCFHTTT_TIMES[5];
    public BPCFHTTT() {
        for (int i=0;i<5;i++) TIMES[i] = new BPCFHTTT_TIMES();
    }
}
