package com.hisun.LN;

public class LNCIRULM_DATA_TXT {
    public char VLDT_FLG = ' ';
    public LNCIRULM_FROM_CNTR[] FROM_CNTR = new LNCIRULM_FROM_CNTR[10];
    public char RATE_TYP = ' ';
    public String INT_FML = " ";
    public short TO_NO = 0;
    public LNCIRULM_DATA_TXT() {
        for (int i=0;i<10;i++) FROM_CNTR[i] = new LNCIRULM_FROM_CNTR();
    }
}
