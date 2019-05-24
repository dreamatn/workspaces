package com.hisun.CM;

public class CMCS3000 {
    public char BUSI_KND = ' ';
    public char TX_TYP = ' ';
    public String CARD_NO = " ";
    public int CARD_SEQ = 0;
    public String TXN_CCY = " ";
    public double TXN_AMT = 0;
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
    public String TXN_RMK = " ";
    public CMCS3000_FEE_DATA[] FEE_DATA = new CMCS3000_FEE_DATA[3];
    public short CARD_SEQ2 = 0;
    public String ARQC = " ";
    public String ARQC_DATA = " ";
    public String VERIFY_RLT = " ";
    public String ISSUE_DATA = " ";
    public CMCS3000() {
        for (int i=0;i<3;i++) FEE_DATA[i] = new CMCS3000_FEE_DATA();
    }
}
