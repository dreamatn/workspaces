package com.hisun.CM;

public class CMCSCRDR {
    public char TR_TYPE = ' ';
    public String REQ_SYS_JRN = " ";
    public int REQ_DATE = 0;
    public String REQ_SYS = " ";
    public String REQ_CHNL = " ";
    public String TL_NO = " ";
    public int TR_BR = 0;
    public String AP_REF = " ";
    public String CHN_BUS_TYP = " ";
    public CMCSCRDR_AC_DATA[] AC_DATA = new CMCSCRDR_AC_DATA[2];
    public CMCSCRDR_MIB_DATA[] MIB_DATA = new CMCSCRDR_MIB_DATA[5];
    public char PROC_STS = ' ';
    public String RET_CODE = " ";
    public String RET_MSG = " ";
    public int DATE = 0;
    public long JRNNO = 0;
    public String VCH_NO = " ";
    public CMCSCRDR_RET_AC_DATA[] RET_AC_DATA = new CMCSCRDR_RET_AC_DATA[2];
    public CMCSCRDR() {
        for (int i=0;i<2;i++) AC_DATA[i] = new CMCSCRDR_AC_DATA();
        for (int i=0;i<5;i++) MIB_DATA[i] = new CMCSCRDR_MIB_DATA();
        for (int i=0;i<2;i++) RET_AC_DATA[i] = new CMCSCRDR_RET_AC_DATA();
    }
}
