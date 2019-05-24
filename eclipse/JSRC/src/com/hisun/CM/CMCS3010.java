package com.hisun.CM;

public class CMCS3010 {
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
    public char ID_FLG = ' ';
    public String ID_TYP = " ";
    public String ID_NO = " ";
    public char NM_FLG = ' ';
    public String CI_NM = " ";
    public char TEL_FLG = ' ';
    public String TEL_NO = " ";
    public String SMR = " ";
    public String MERCH_CD = " ";
    public String MERCH_NM = " ";
    public String MERCH_AC = " ";
    public CMCS3010_FEE_DATA[] FEE_DATA = new CMCS3010_FEE_DATA[3];
    public String REQ_ID = " ";
    public int REQ_DATE = 0;
    public String REQ_JRN = " ";
    public CMCS3010() {
        for (int i=0;i<3;i++) FEE_DATA[i] = new CMCS3010_FEE_DATA();
    }
}
