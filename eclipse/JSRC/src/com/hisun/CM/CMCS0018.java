package com.hisun.CM;

public class CMCS0018 {
    public char TR_TYPE = ' ';
    public String REQ_JRN = " ";
    public int REQ_DATE = 0;
    public String REQ_SYS = " ";
    public String REQ_CHNL = " ";
    public String TL_NO = " ";
    public int TR_BR = 0;
    public String AP_REF = " ";
    public String CHN_TYP = " ";
    public CMCS0018_AC_DATA[] AC_DATA = new CMCS0018_AC_DATA[9];
    public String P_IN_AC = " ";
    public String INT_AC = " ";
    public String ERR_AC = " ";
    public String F_PRO_CD = " ";
    public String FIN_AC = " ";
    public CMCS0018_EVE_DATA[] EVE_DATA = new CMCS0018_EVE_DATA[4];
    public CMCS0018_TAX_RATE TAX_RATE = new CMCS0018_TAX_RATE();
    public String RMK = " ";
    public char PROC_STS = ' ';
    public String RET_CODE = " ";
    public String RET_MSG = " ";
    public int DATE = 0;
    public long JRNNO = 0;
    public String VCH_NO = " ";
    public CMCS0018_RET_DATA[] RET_DATA = new CMCS0018_RET_DATA[9];
    public double BAL_AMT = 0;
    public double INT_AMT = 0;
    public String RET_CREV = " ";
    public CMCS0018() {
        for (int i=0;i<9;i++) AC_DATA[i] = new CMCS0018_AC_DATA();
        for (int i=0;i<4;i++) EVE_DATA[i] = new CMCS0018_EVE_DATA();
        for (int i=0;i<9;i++) RET_DATA[i] = new CMCS0018_RET_DATA();
    }
}
