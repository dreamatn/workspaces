package com.hisun.BP;

public class BPB4051_AWA_4051 {
    public int BR = 0;
    public short BR_NO = 0;
    public String MODNO = " ";
    public short MODNO_NO = 0;
    public String GL_BOOK = " ";
    public short GL_BOOK_NO = 0;
    public String NAME = " ";
    public short NAME_NO = 0;
    public String EVT_TYPE = " ";
    public short EVT_TYPE_NO = 0;
    public short SORT_SEQ = 0;
    public short SORT_SEQ_NO = 0;
    public String TR_GROUP = " ";
    public short TR_GROUP_NO = 0;
    public char GL_PNT = ' ';
    public short GL_PNT_NO = 0;
    public char DR_CR = ' ';
    public short DR_CR_NO = 0;
    public String ITM_CODE = " ";
    public short ITM_CODE_NO = 0;
    public String ITM_PNT = " ";
    public short ITM_PNT_NO = 0;
    public char BR_FLG = ' ';
    public short BR_FLG_NO = 0;
    public short CCY_PNT = 0;
    public short CCY_PNT_NO = 0;
    public char PST_AUTH = ' ';
    public short PST_AUTH_NO = 0;
    public char RM_FLG = ' ';
    public short RM_FLG_NO = 0;
    public short EFF_DAYS = 0;
    public short EFF_DAYS_NO = 0;
    public BPB4051_AMT_MTD[] AMT_MTD = new BPB4051_AMT_MTD[60];
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public int UPD_DATE = 0;
    public short UPD_DATE_NO = 0;
    public int UPD_TIME = 0;
    public short UPD_TIME_NO = 0;
    public short EVT_POS = 0;
    public short EVT_POS_NO = 0;
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String CNTR_TYP = " ";
    public short CNTR_TYP_NO = 0;
    public String PROD_TYP = " ";
    public short PROD_TYP_NO = 0;
    public int BR2 = 0;
    public short BR2_NO = 0;
    public BPB4051_AWA_4051() {
        for (int i=0;i<60;i++) AMT_MTD[i] = new BPB4051_AMT_MTD();
    }
}
