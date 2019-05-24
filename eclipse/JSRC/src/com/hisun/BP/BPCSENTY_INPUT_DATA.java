package com.hisun.BP;

public class BPCSENTY_INPUT_DATA {
    public char FUNC = ' ';
    public String CNTR_TYPE = " ";
    public String PROD_TYPE = " ";
    public int BR_AC = 0;
    public String MODNO = " ";
    public String EVENT_TYPE = " ";
    public String GL_BOOK = " ";
    public short SORT_SEQ = 0;
    public String TR_GROUP = " ";
    public char GLMST_PNT = ' ';
    public char DR_CR = ' ';
    public String ITM_CODE = " ";
    public String ITM_PNT = " ";
    public char BR_FLG = ' ';
    public int BR = 0;
    public short CCY_PNT = 0;
    public char POST_AUTH = ' ';
    public char RM_FLG = ' ';
    public short EFF_DAYS = 0;
    public BPCSENTY_AMT_METHOD[] AMT_METHOD = new BPCSENTY_AMT_METHOD[60];
    public String REMARK = " ";
    public BPCSENTY_HIDE_INFO HIDE_INFO = new BPCSENTY_HIDE_INFO();
    public BPCSENTY_INPUT_DATA() {
        for (int i=0;i<60;i++) AMT_METHOD[i] = new BPCSENTY_AMT_METHOD();
    }
}
