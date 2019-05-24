package com.hisun.BP;

public class BPCI4081 {
    public String CNTR_TYP = " ";
    public String PROD_TYP = " ";
    public int BR2 = 0;
    public String MODNO = " ";
    public String NAME = " ";
    public String EVT_TYPE = " ";
    public String GL_BOOK = " ";
    public short SORT_SEQ = 0;
    public String TR_GROUP = " ";
    public char GL_PNT = ' ';
    public char DR_CR = ' ';
    public String ITM_CODE = " ";
    public String ITM_PNT = " ";
    public char BR_FLG = ' ';
    public int BR = 0;
    public short CCY_PNT = 0;
    public char PST_AUTH = ' ';
    public char RM_FLG = ' ';
    public short EFF_DAYS = 0;
    public BPCI4081_AMT_MTD[] AMT_MTD = new BPCI4081_AMT_MTD[20];
    public String REMARK = " ";
    public char FUNC = ' ';
    public int UPD_DATE = 0;
    public int UPD_TIME = 0;
    public short EVT_POS = 0;
    public BPCI4081() {
        for (int i=0;i<20;i++) AMT_MTD[i] = new BPCI4081_AMT_MTD();
    }
}
