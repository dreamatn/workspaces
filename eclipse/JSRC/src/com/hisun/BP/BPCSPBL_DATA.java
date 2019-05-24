package com.hisun.BP;

public class BPCSPBL_DATA {
    public String ITM_PNT = " ";
    public String ITM_NO = " ";
    public int ITM_SEQ = 0;
    public BPCSPBL_AMT_PNT[] AMT_PNT = new BPCSPBL_AMT_PNT[76];
    public String REMARK = " ";
    public String CNTR_TYP = " ";
    public String PROD_TYP = " ";
    public int BR = 0;
    public BPCSPBL_DATA() {
        for (int i=0;i<76;i++) AMT_PNT[i] = new BPCSPBL_AMT_PNT();
    }
}
