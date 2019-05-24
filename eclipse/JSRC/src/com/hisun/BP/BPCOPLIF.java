package com.hisun.BP;

public class BPCOPLIF {
    public int BR = 0;
    public String TLR = " ";
    public int TR_DT = 0;
    public char PLBOX_TYPE = ' ';
    public String PLBOX_NO = " ";
    public char IN_FLAG = ' ';
    public String IN_CCY = " ";
    public double IN_D_AMT = 0;
    public char FILLER9 = 0X01;
    public double IN_R_AMT = 0;
    public char FILLER11 = 0X01;
    public double IN_S_AMT = 0;
    public char FILLER13 = 0X01;
    public String IN_CASH_TYPE = " ";
    public BPCOPLIF_IN_PVAL_INFO[] IN_PVAL_INFO = new BPCOPLIF_IN_PVAL_INFO[12];
    public char OT_FLAG = ' ';
    public String OT_CCY = " ";
    public double OT_D_AMT = 0;
    public char FILLER23 = 0X01;
    public double OT_R_AMT = 0;
    public char FILLER25 = 0X01;
    public double OT_S_AMT = 0;
    public char FILLER27 = 0X01;
    public String OT_CASH_TYPE = " ";
    public BPCOPLIF_OT_PVAL_INFO[] OT_PVAL_INFO = new BPCOPLIF_OT_PVAL_INFO[12];
    public BPCOPLIF() {
        for (int i=0;i<12;i++) IN_PVAL_INFO[i] = new BPCOPLIF_IN_PVAL_INFO();
        for (int i=0;i<12;i++) OT_PVAL_INFO[i] = new BPCOPLIF_OT_PVAL_INFO();
    }
}
