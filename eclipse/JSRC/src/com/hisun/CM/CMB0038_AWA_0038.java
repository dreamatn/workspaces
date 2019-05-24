package com.hisun.CM;

public class CMB0038_AWA_0038 {
    public char TR_TYPE = ' ';
    public short TR_TYPE_NO = 0;
    public String REQ_JRN = " ";
    public short REQ_JRN_NO = 0;
    public int REQ_DATE = 0;
    public short REQ_DATE_NO = 0;
    public String REQ_SYS = " ";
    public short REQ_SYS_NO = 0;
    public String REQ_CHNL = " ";
    public short REQ_CHNL_NO = 0;
    public String TL_NO = " ";
    public short TL_NO_NO = 0;
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public String AP_REF = " ";
    public short AP_REF_NO = 0;
    public CMB0038_AC_DATA[] AC_DATA = new CMB0038_AC_DATA[20];
    public char PROC_STS = ' ';
    public short PROC_STS_NO = 0;
    public String RET_CODE = " ";
    public short RET_CODE_NO = 0;
    public String RET_MSG = " ";
    public short RET_MSG_NO = 0;
    public int DATE = 0;
    public short DATE_NO = 0;
    public long JRNNO = 0;
    public short JRNNO_NO = 0;
    public String VCH_NO = " ";
    public short VCH_NO_NO = 0;
    public CMB0038_AWA_0038() {
        for (int i=0;i<20;i++) AC_DATA[i] = new CMB0038_AC_DATA();
    }
}
