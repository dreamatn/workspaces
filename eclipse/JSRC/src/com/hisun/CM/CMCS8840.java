package com.hisun.CM;

public class CMCS8840 {
    public char AC_TYP1 = ' ';
    public String AC1 = " ";
    public int AC_SEQ1 = 0;
    public String CCY_NO1 = " ";
    public char CCY_TYP1 = ' ';
    public String ACO_AC1 = " ";
    public char PSW_FLG = ' ';
    public String PSW = " ";
    public char BV_TYP1 = ' ';
    public String BV_CD1 = " ";
    public char TRK_FLG = ' ';
    public String TRK2_DAT = " ";
    public String TRK3_DAT = " ";
    public char NM_FLG1 = ' ';
    public String CI_NM1 = " ";
    public char ID_FLG1 = ' ';
    public String ID_TYP1 = " ";
    public String ID_NO1 = " ";
    public String RMK1 = " ";
    public char AC_TYP2 = ' ';
    public String AC2 = " ";
    public int AC_SEQ2 = 0;
    public String ACO_AC2 = " ";
    public char BV_TYP2 = ' ';
    public String BV_NO2 = " ";
    public char NM_FLG2 = ' ';
    public String CI_NM2 = " ";
    public char ID_FLG2 = ' ';
    public String ID_TYP2 = " ";
    public String ID_NO2 = " ";
    public String RMK2 = " ";
    public String CCY_NO2 = " ";
    public char CCY_TYP2 = ' ';
    public double AMT = 0;
    public char SMNM_FLG = ' ';
    public char FC_FLG = ' ';
    public int VAL_DT = 0;
    public String HLD_NO = " ";
    public CMCS8840_FEE_DATA[] FEE_DATA = new CMCS8840_FEE_DATA[3];
    public String DESC = " ";
    public String SMR = " ";
    public CMCS8840() {
        for (int i=0;i<3;i++) FEE_DATA[i] = new CMCS8840_FEE_DATA();
    }
}
