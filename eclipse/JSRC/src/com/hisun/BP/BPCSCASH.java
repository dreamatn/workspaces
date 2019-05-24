package com.hisun.BP;

public class BPCSCASH {
    public BPCSCASH_RC RC = new BPCSCASH_RC();
    public String OUTPUT_FMT = " ";
    public char VB_FLAG = ' ';
    public char PLBOX_TYPE = ' ';
    public String TLR = " ";
    public int BR = 0;
    public int UPT_DT = 0;
    public String CCY = " ";
    public double EXCH_AMT = 0;
    public String IN_CASH_TYPE = " ";
    public char IN_CS_KIND = ' ';
    public BPCSCASH_IN_PVAL_INFO[] IN_PVAL_INFO = new BPCSCASH_IN_PVAL_INFO[12];
    public String OUT_CASH_TYPE = " ";
    public char OUT_CS_KIND = ' ';
    public BPCSCASH_OUT_PVAL_INFO[] OUT_PVAL_INFO = new BPCSCASH_OUT_PVAL_INFO[12];
    public BPCSCASH() {
        for (int i=0;i<12;i++) IN_PVAL_INFO[i] = new BPCSCASH_IN_PVAL_INFO();
        for (int i=0;i<12;i++) OUT_PVAL_INFO[i] = new BPCSCASH_OUT_PVAL_INFO();
    }
}
