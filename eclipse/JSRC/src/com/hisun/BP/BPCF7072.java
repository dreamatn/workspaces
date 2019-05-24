package com.hisun.BP;

public class BPCF7072 {
    public short TOTAL_NUM = 0;
    public char END_FLG = ' ';
    public long END_POS = 0;
    public BPCF7072_TIMES[] TIMES = new BPCF7072_TIMES[15];
    public BPCF7072() {
        for (int i=0;i<15;i++) TIMES[i] = new BPCF7072_TIMES();
    }
}
