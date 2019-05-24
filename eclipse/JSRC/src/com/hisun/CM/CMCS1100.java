package com.hisun.CM;

public class CMCS1100 {
    public String CHN_TYP = " ";
    public CMCS1100_AC_DATA[] AC_DATA = new CMCS1100_AC_DATA[2];
    public CMCS1100_MIB_DATA[] MIB_DATA = new CMCS1100_MIB_DATA[10];
    public CMCS1100_CASH_DAT[] CASH_DAT = new CMCS1100_CASH_DAT[6];
    public String HLD_NO = " ";
    public char HLD_TYP = ' ';
    public double HLD_AMT = 0;
    public int VAL_DATE = 0;
    public int EXP_DATE = 0;
    public String HLD_RSN = " ";
    public CMCS1100_FEE_DATA[] FEE_DATA = new CMCS1100_FEE_DATA[5];
    public String VBV_CODE = " ";
    public String HEAD_NO = " ";
    public String BEG_NO = " ";
    public String END_NO = " ";
    public int NUM = 0;
    public String REMARK = " ";
    public String NARRATIVE = " ";
    public CMCS1100() {
        for (int i=0;i<2;i++) AC_DATA[i] = new CMCS1100_AC_DATA();
        for (int i=0;i<10;i++) MIB_DATA[i] = new CMCS1100_MIB_DATA();
        for (int i=0;i<6;i++) CASH_DAT[i] = new CMCS1100_CASH_DAT();
        for (int i=0;i<5;i++) FEE_DATA[i] = new CMCS1100_FEE_DATA();
    }
}
