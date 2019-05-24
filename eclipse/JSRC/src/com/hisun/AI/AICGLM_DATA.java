package com.hisun.AI;

public class AICGLM_DATA {
    public String COA_FLG = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public char OPT_FLG = ' ';
    public String CNTY1 = " ";
    public String CNTY2 = " ";
    public String CNTY3 = " ";
    public char CKFLG = ' ';
    public String REAL_GL = " ";
    public String MEMO_GL = " ";
    public AICGLM_REL_ITMS[] REL_ITMS = new AICGLM_REL_ITMS[60];
    public String UPD_TLR = " ";
    public int UPD_DATE = 0;
    public int UPD_TIME = 0;
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public String TS = " ";
    public AICGLM_DATA() {
        for (int i=0;i<60;i++) REL_ITMS[i] = new AICGLM_REL_ITMS();
    }
}
