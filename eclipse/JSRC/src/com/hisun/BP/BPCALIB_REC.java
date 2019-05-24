package com.hisun.BP;

public class BPCALIB_REC {
    public int SEQ = 0;
    public int APP_DT = 0;
    public int APP_NO = 0;
    public int APP_BR = 0;
    public String BR_NM = " ";
    public String APP_TLR = " ";
    public int UP_BR = 0;
    public String TR_TLR = " ";
    public char ALLOT_TYPE = ' ';
    public String APP_CCY = " ";
    public double APP_AMT = 0;
    public double OUT_AMT = 0;
    public BPCALIB_PVAL_INFO[] PVAL_INFO = new BPCALIB_PVAL_INFO[20];
    public BPCALIB_REC() {
        for (int i=0;i<20;i++) PVAL_INFO[i] = new BPCALIB_PVAL_INFO();
    }
}
