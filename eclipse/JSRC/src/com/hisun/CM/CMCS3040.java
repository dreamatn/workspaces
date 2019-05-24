package com.hisun.CM;

public class CMCS3040 {
    public char BUSI_KND = ' ';
    public char TX_TYP = ' ';
    public String CARD_NO = " ";
    public int CARD_SEQ = 0;
    public String OPP_AC = " ";
    public String TXN_CCY = " ";
    public double TXN_AMT = 0;
    public String TXN_PSW = " ";
    public String TXN_RMK = " ";
    public String TRK2_DAT = " ";
    public String TRK3_DAT = " ";
    public String CVN = " ";
    public char CITY_FLG = ' ';
    public String AREA_CD = " ";
    public String SLT_AC = " ";
    public String SLT_NO = " ";
    public char ID_FLG = ' ';
    public String ID_TYP = " ";
    public String ID_NO = " ";
    public char NM_FLG = ' ';
    public String CI_NM = " ";
    public char TEL_FLG = ' ';
    public String TEL_NO = " ";
    public String SMR = " ";
    public int STL_DT = 0;
    public String OPP_NM = " ";
    public int OPP_BR = 0;
    public String OPP_BRNM = " ";
    public CMCS3040_FEE_DATA[] FEE_DATA = new CMCS3040_FEE_DATA[3];
    public CMCS3040() {
        for (int i=0;i<3;i++) FEE_DATA[i] = new CMCS3040_FEE_DATA();
    }
}
