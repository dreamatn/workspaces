package com.hisun.BP;

public class BPCOCHGF {
    public String SVR_CD = " ";
    public String FEE_DESC = " ";
    public char FILLER3 = 0X02;
    public String CHNL = " ";
    public String RGN_NO = " ";
    public String PRD_CODE = " ";
    public String FREE_FMT = " ";
    public String CFEE_CCY = " ";
    public String CI_NO = " ";
    public String AC_NO = " ";
    public BPCOCHGF_INFO[] INFO = new BPCOCHGF_INFO[20];
    public short ACC_CNT = 0;
    public double TOT_AMT = 0;
    public char CHG_FLG = ' ';
    public String CHG_CCY = " ";
    public double CHG_AMT = 0;
    public String FEE_AC = " ";
    public String CHQ_NO = " ";
    public int CR_BANK = 0;
    public int DR_BANK = 0;
    public long ACCT_CD = 0;
    public String RMK = " ";
    public char FILLER25 = 0X02;
    public String AC_CNAME = " ";
    public char FILLER27 = 0X02;
    public String AC_ENAME = " ";
    public int BR = 0;
    public String REMK = " ";
    public char FILLER31 = 0X02;
    public String TX_MMO = " ";
    public String CARD_NO = " ";
    public String FEE_CTRT = " ";
    public int FEE_DPTM = 0;
    public String FEE_CONT = " ";
    public String FEE_ADVICE = " ";
    public char CCY_TYPE = ' ';
    public String BSNS_NO = " ";
    public String CREV_NO = " ";
    public BPCOCHGF() {
        for (int i=0;i<20;i++) INFO[i] = new BPCOCHGF_INFO();
    }
}
