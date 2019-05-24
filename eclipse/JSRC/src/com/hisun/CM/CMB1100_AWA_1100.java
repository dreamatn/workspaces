package com.hisun.CM;

public class CMB1100_AWA_1100 {
    public String CHN_TYP = " ";
    public short CHN_TYP_NO = 0;
    public CMB1100_AC_DATA[] AC_DATA = new CMB1100_AC_DATA[2];
    public CMB1100_MIB_DATA[] MIB_DATA = new CMB1100_MIB_DATA[10];
    public CMB1100_CASH_DAT[] CASH_DAT = new CMB1100_CASH_DAT[6];
    public String HLD_NO = " ";
    public short HLD_NO_NO = 0;
    public char HLD_TYP = ' ';
    public short HLD_TYP_NO = 0;
    public double HLD_AMT = 0;
    public short HLD_AMT_NO = 0;
    public int VAL_DATE = 0;
    public short VAL_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public String HLD_RSN = " ";
    public short HLD_RSN_NO = 0;
    public CMB1100_FEE_DATA[] FEE_DATA = new CMB1100_FEE_DATA[5];
    public String VBV_CODE = " ";
    public short VBV_CODE_NO = 0;
    public String HEAD_NO = " ";
    public short HEAD_NO_NO = 0;
    public String BEG_NO = " ";
    public short BEG_NO_NO = 0;
    public String END_NO = " ";
    public short END_NO_NO = 0;
    public int NUM = 0;
    public short NUM_NO = 0;
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public String NARTIVE = " ";
    public short NARTIVE_NO = 0;
    public CMB1100_AWA_1100() {
        for (int i=0;i<2;i++) AC_DATA[i] = new CMB1100_AC_DATA();
        for (int i=0;i<10;i++) MIB_DATA[i] = new CMB1100_MIB_DATA();
        for (int i=0;i<6;i++) CASH_DAT[i] = new CMB1100_CASH_DAT();
        for (int i=0;i<5;i++) FEE_DATA[i] = new CMB1100_FEE_DATA();
    }
}
