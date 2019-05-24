package com.hisun.CM;

public class CMB1600_AWA_1600 {
    public String CHN_TYP = " ";
    public short CHN_TYP_NO = 0;
    public CMB1600_AC_DATA[] AC_DATA = new CMB1600_AC_DATA[6];
    public CMB1600_MIB_DATA[] MIB_DATA = new CMB1600_MIB_DATA[10];
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
    public CMB1600_FEE_DATA[] FEE_DATA = new CMB1600_FEE_DATA[5];
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public CMB1600_AWA_1600() {
        for (int i=0;i<6;i++) AC_DATA[i] = new CMB1600_AC_DATA();
        for (int i=0;i<10;i++) MIB_DATA[i] = new CMB1600_MIB_DATA();
        for (int i=0;i<5;i++) FEE_DATA[i] = new CMB1600_FEE_DATA();
    }
}
