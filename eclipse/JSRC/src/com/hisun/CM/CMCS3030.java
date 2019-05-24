package com.hisun.CM;

public class CMCS3030 {
    public char BUSI_KND = ' ';
    public char TX_TYP = ' ';
    public String CARD_NO = " ";
    public int CARD_SEQ = 0;
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
    public String NARRATIVE = " ";
    public int STL_DT = 0;
    public CMCS3030_FEE_DATA[] FEE_DATA = new CMCS3030_FEE_DATA[3];
    public String RLT_AC = " ";
    public String RLT_NM = " ";
    public String MERCH_NM = " ";
    public String AC_NAME = " ";
    public String ID_NO1 = " ";
    public CMCS3030() {
        for (int i=0;i<3;i++) FEE_DATA[i] = new CMCS3030_FEE_DATA();
    }
}
