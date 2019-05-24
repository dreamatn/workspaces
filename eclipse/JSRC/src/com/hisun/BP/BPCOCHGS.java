package com.hisun.BP;

public class BPCOCHGS {
    public char AC_TYP = ' ';
    public String CI_NO = " ";
    public BPCOCHGS_INFO[] INFO = new BPCOCHGS_INFO[20];
    public char CHG_FLG = ' ';
    public String CHG_CCY = " ";
    public String FEE_AC = " ";
    public String CHQ_NO = " ";
    public int DATE = 0;
    public String RMK = " ";
    public char FILLER15 = 0X02;
    public String AC_CNAME = " ";
    public char FILLER17 = 0X02;
    public String AC_ENAME = " ";
    public int BR = 0;
    public String TX_MMO = " ";
    public String CARD_NO = " ";
    public String FEE_CTRT = " ";
    public char CCY_TYPE = ' ';
    public String BSNS_NO = " ";
    public String CREV_NO = " ";
    public BPCOCHGS() {
        for (int i=0;i<20;i++) INFO[i] = new BPCOCHGS_INFO();
    }
}
