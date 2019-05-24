package com.hisun.CM;

public class CMCS8860 {
    public String CHN_TYP = " ";
    public CMCS8860_AC_DATA[] AC_DATA = new CMCS8860_AC_DATA[2];
    public CMCS8860_MIB_DATA[] MIB_DATA = new CMCS8860_MIB_DATA[10];
    public String FIN_PROD = " ";
    public String FIN_AC = " ";
    public String HLD_NO = " ";
    public char HLD_TYP = ' ';
    public double HLD_AMT = 0;
    public int VAL_DATE = 0;
    public int EXP_DATE = 0;
    public String HLD_RSN = " ";
    public String VBV_CODE = " ";
    public String HEAD_NO = " ";
    public String BEG_NO = " ";
    public String END_NO = " ";
    public int NUM = 0;
    public String REMARK = " ";
    public CMCS8860() {
        for (int i=0;i<2;i++) AC_DATA[i] = new CMCS8860_AC_DATA();
        for (int i=0;i<10;i++) MIB_DATA[i] = new CMCS8860_MIB_DATA();
    }
}
