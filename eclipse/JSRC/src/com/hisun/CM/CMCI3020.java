package com.hisun.CM;

public class CMCI3020 {
    public char BUSI_KND = ' ';
    public char TX_TYP = ' ';
    public String CARD_NO = " ";
    public int CARD_SEQ = 0;
    public String TXN_CCY = " ";
    public double TXN_AMT = 0;
    public String TXN_RMK = " ";
    public char TXN_FLG = ' ';
    public String TXN_PSW = " ";
    public String TRK2_DAT = " ";
    public String TRK3_DAT = " ";
    public String CVN = " ";
    public char CITY_FLG = ' ';
    public String AREA_CD = " ";
    public int STL_DT = 0;
    public String SLT_AC = " ";
    public String SLT_NO = " ";
    public char ID_FLG = ' ';
    public String ID_TYP = " ";
    public String ID_NO = " ";
    public char NM_FLG = ' ';
    public String CI_NM = " ";
    public char TEL_FLG = ' ';
    public String TEL_NO = " ";
    public String NARATVE = " ";
    public String MERCH_CD = " ";
    public String MERCH_NM = " ";
    public String RLT_AC = " ";
    public CMCI3020_FEE_DATA[] FEE_DATA = new CMCI3020_FEE_DATA[3];
    public String REQ_ID = " ";
    public int REQ_DATE = 0;
    public String REQ_JRN = " ";
    public String RLT_NM = " ";
    public CMCI3020() {
        for (int i=0;i<3;i++) FEE_DATA[i] = new CMCI3020_FEE_DATA();
    }
}
