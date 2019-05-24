package com.hisun.CM;

public class CMB3000_AWA_3000 {
    public char BUSI_KND = ' ';
    public short BUSI_KND_NO = 0;
    public char TX_TYP = ' ';
    public short TX_TYP_NO = 0;
    public String CARD_NO = " ";
    public short CARD_NO_NO = 0;
    public int CARD_SEQ = 0;
    public short CARD_SEQ_NO = 0;
    public String TXN_CCY = " ";
    public short TXN_CCY_NO = 0;
    public double TXN_AMT = 0;
    public short TXN_AMT_NO = 0;
    public String TXN_PSW = " ";
    public short TXN_PSW_NO = 0;
    public String TRK2_DAT = " ";
    public short TRK2_DAT_NO = 0;
    public String TRK3_DAT = " ";
    public short TRK3_DAT_NO = 0;
    public String CVN = " ";
    public short CVN_NO = 0;
    public char CITY_FLG = ' ';
    public short CITY_FLG_NO = 0;
    public char ID_FLG = ' ';
    public short ID_FLG_NO = 0;
    public String ID_TYP = " ";
    public short ID_TYP_NO = 0;
    public String ID_NO = " ";
    public short ID_NO_NO = 0;
    public char NM_FLG = ' ';
    public short NM_FLG_NO = 0;
    public String CI_NM = " ";
    public short CI_NM_NO = 0;
    public char TEL_FLG = ' ';
    public short TEL_FLG_NO = 0;
    public String TEL_NO = " ";
    public short TEL_NO_NO = 0;
    public String SMR = " ";
    public short SMR_NO = 0;
    public String TXN_RMK = " ";
    public short TXN_RMK_NO = 0;
    public CMB3000_FEE_DATA[] FEE_DATA = new CMB3000_FEE_DATA[3];
    public short CARD_SQ2 = 0;
    public short CARD_SQ2_NO = 0;
    public String ARQC = " ";
    public short ARQC_NO = 0;
    public String ARQC_DAT = " ";
    public short ARQC_DAT_NO = 0;
    public String VERY_RLT = " ";
    public short VERY_RLT_NO = 0;
    public String ISSU_DAT = " ";
    public short ISSU_DAT_NO = 0;
    public CMB3000_AWA_3000() {
        for (int i=0;i<3;i++) FEE_DATA[i] = new CMB3000_FEE_DATA();
    }
}
