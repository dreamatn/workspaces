package com.hisun.BP;

public class BPCOENTY_LIST_DATA {
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
    public BPCOENTY_AMT_METHOD[] AMT_METHOD = new BPCOENTY_AMT_METHOD[60];
    public String REMARK = " ";
    public char FILLER38 = 0X02;
    public short EVENT_POS = 0;
    public BPCOENTY_LIST_DATA() {
        for (int i=0;i<60;i++) AMT_METHOD[i] = new BPCOENTY_AMT_METHOD();
    }
}
