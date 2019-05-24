package com.hisun.BP;

public class BPCHGLM_DATA {
    public String COA_FLG = " ";
    public String SNAME = " ";
    public String LNAME = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public String CNTY1 = " ";
    public String CNTY2 = " ";
    public String CNTY3 = " ";
    public char CKFLG = ' ';
    public String REAL_GL = " ";
    public String MEMO_GL = " ";
    public BPCHGLM_REL_ITMS[] REL_ITMS = new BPCHGLM_REL_ITMS[60];
    public BPCHGLM_DATA() {
        for (int i=0;i<60;i++) REL_ITMS[i] = new BPCHGLM_REL_ITMS();
    }
}
