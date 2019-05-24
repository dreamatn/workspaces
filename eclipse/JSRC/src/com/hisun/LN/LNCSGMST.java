package com.hisun.LN;

public class LNCSGMST {
    public LNCSGMST_RC RC = new LNCSGMST_RC();
    public char FUNC = ' ';
    public char GMST_OPT = ' ';
    public LNCSGMST_DATA DATA = new LNCSGMST_DATA();
    public LNCSGMST_DIARY DIARY = new LNCSGMST_DIARY();
    public char ROLE_TYPE = ' ';
    public short CNT = 0;
    public String CUSTM1 = " ";
    public String CUSTM2 = " ";
    public String CUSTM3 = " ";
    public String BAR_CD = " ";
    public String UNIT_CD = " ";
    public String FEE_ACT = " ";
    public String FEE_AC = " ";
    public LNCSGMST_FEE_INFO[] FEE_INFO = new LNCSGMST_FEE_INFO[5];
    public LNCSGMST() {
        for (int i=0;i<5;i++) FEE_INFO[i] = new LNCSGMST_FEE_INFO();
    }
}
