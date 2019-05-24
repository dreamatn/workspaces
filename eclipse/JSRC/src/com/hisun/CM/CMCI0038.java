package com.hisun.CM;

public class CMCI0038 {
    public char TR_TYPE = ' ';
    public String REQ_JRN = " ";
    public int REQ_DATE = 0;
    public String REQ_SYS = " ";
    public String REQ_CHNL = " ";
    public String TL_NO = " ";
    public int TR_BR = 0;
    public String AP_REF = " ";
    public CMCI0038_AC_DATA[] AC_DATA = new CMCI0038_AC_DATA[20];
    public char PROC_STS = ' ';
    public String RET_CODE = " ";
    public String RET_MSG = " ";
    public int DATE = 0;
    public long JRNNO = 0;
    public String VCH_NO = " ";
    public CMCI0038() {
        for (int i=0;i<20;i++) AC_DATA[i] = new CMCI0038_AC_DATA();
    }
}
