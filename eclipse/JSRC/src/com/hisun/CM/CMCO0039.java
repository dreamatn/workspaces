package com.hisun.CM;

public class CMCO0039 {
    public char TR_TYPE = ' ';
    public String REQ_JRN = " ";
    public int REQ_DATE = 0;
    public String REQ_SYS = " ";
    public String REQ_CHNL = " ";
    public String TL_NO = " ";
    public int TR_BR = 0;
    public String AP_REF = " ";
    public CMCO0039_AC_DATA[] AC_DATA = new CMCO0039_AC_DATA[20];
    public char PROC_STS = ' ';
    public String RET_CODE = " ";
    public String RET_MSG = " ";
    public int DATE = 0;
    public long JRNNO = 0;
    public String VCH_NO = " ";
    public CMCO0039() {
        for (int i=0;i<20;i++) AC_DATA[i] = new CMCO0039_AC_DATA();
    }
}
