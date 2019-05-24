package com.hisun.BP;

public class BPCFEHIT {
    public short TOTAL_NUM = 0;
    public short TOD_REC_NUM = 0;
    public long END_POS = 0;
    public char END_FLG = ' ';
    public BPCFEHIT_TIMES[] TIMES = new BPCFEHIT_TIMES[8];
    public BPCFEHIT() {
        for (int i=0;i<8;i++) TIMES[i] = new BPCFEHIT_TIMES();
    }
}
