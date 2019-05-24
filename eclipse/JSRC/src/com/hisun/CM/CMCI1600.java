package com.hisun.CM;

public class CMCI1600 {
    public String CHN_TYP = " ";
    public CMCI1600_AC_DATA[] AC_DATA = new CMCI1600_AC_DATA[6];
    public CMCI1600_MIB_DATA[] MIB_DATA = new CMCI1600_MIB_DATA[10];
    public String HLD_NO = " ";
    public char HLD_TYP = ' ';
    public double HLD_AMT = 0;
    public int VAL_DATE = 0;
    public int EXP_DATE = 0;
    public String HLD_RSN = " ";
    public CMCI1600_FEE_DATA[] FEE_DATA = new CMCI1600_FEE_DATA[5];
    public String REMARK = " ";
    public CMCI1600() {
        for (int i=0;i<6;i++) AC_DATA[i] = new CMCI1600_AC_DATA();
        for (int i=0;i<10;i++) MIB_DATA[i] = new CMCI1600_MIB_DATA();
        for (int i=0;i<5;i++) FEE_DATA[i] = new CMCI1600_FEE_DATA();
    }
}
