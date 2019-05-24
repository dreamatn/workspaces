package com.hisun.LN;

public class LNCIRULB_DATA_TXT {
    public char VLDT_FLG = ' ';
    public LNCIRULB_FR_CNTR[] FR_CNTR = new LNCIRULB_FR_CNTR[10];
    public char RATE_TYP = ' ';
    public String INT_FML = " ";
    public LNCIRULB_TO_CNTR TO_CNTR = new LNCIRULB_TO_CNTR();
    public LNCIRULB_DATA_TXT() {
        for (int i=0;i<10;i++) FR_CNTR[i] = new LNCIRULB_FR_CNTR();
    }
}
