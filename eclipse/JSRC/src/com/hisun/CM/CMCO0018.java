package com.hisun.CM;

public class CMCO0018 {
    public char TR_TYPE = ' ';
    public String REQ_SYS_JRN = " ";
    public int REQ_DATE = 0;
    public int REQ_AC_DATE = 0;
    public String REQ_SYS = " ";
    public String REQ_CHNL = " ";
    public String TL_NO = " ";
    public int TR_BR = 0;
    public String AP_REF = " ";
    public String CHN_BUS_TYP = " ";
    public CMCO0018_AC_DATA[] AC_DATA = new CMCO0018_AC_DATA[9];
    public String PAL_IN_AC = " ";
    public String INT_IN_AC = " ";
    public String ERR_PRC_AC = " ";
    public String FINACL_PROD_CD = " ";
    public String FINACL_AC = " ";
    public CMCO0018_EVE_DATA[] EVE_DATA = new CMCO0018_EVE_DATA[4];
    public CMCO0018_TAX_RATE TAX_RATE = new CMCO0018_TAX_RATE();
    public String RMK = " ";
    public char PROC_STS = ' ';
    public String RET_CODE = " ";
    public String RET_MSG = " ";
    public int DATE = 0;
    public long JRNNO = 0;
    public String VCH_NO = " ";
    public CMCO0018_RET_AC_DATA[] RET_AC_DATA = new CMCO0018_RET_AC_DATA[9];
    public double BAL_IN_AMT = 0;
    public double INT_IN_AMT = 0;
    public String RET_ERR_CREV = " ";
    public CMCO0018() {
        for (int i=0;i<9;i++) AC_DATA[i] = new CMCO0018_AC_DATA();
        for (int i=0;i<4;i++) EVE_DATA[i] = new CMCO0018_EVE_DATA();
        for (int i=0;i<9;i++) RET_AC_DATA[i] = new CMCO0018_RET_AC_DATA();
    }
}
