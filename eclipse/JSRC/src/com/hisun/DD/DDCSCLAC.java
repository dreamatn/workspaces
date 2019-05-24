package com.hisun.DD;

public class DDCSCLAC {
    public char BV_TYP = ' ';
    public String DR_CARD = " ";
    public String AC_NO = " ";
    public String AC_CNM = " ";
    public String AC_ENM = " ";
    public String PSBK_NO = " ";
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public String DRW_PSW = " ";
    public char TRF_FLG = ' ';
    public String MMO = " ";
    public char TO_BVTYP = ' ';
    public String TO_CARD = " ";
    public String TRF_AC = " ";
    public double AC_BAL = 0;
    public double AC_INT = 0;
    public double INT_TAX = 0;
    public double TOT_BAL = 0;
    public String NARRATIVE = " ";
    public String REMARK = " ";
    public char GD_WITHDR_FLG = ' ';
    public double FEE_AMT = 0;
    public char CHK_PSW = ' ';
    public String TRK_DT2 = " ";
    public String TRK_DT3 = " ";
    public DDCSCLAC_CARD_INFO[] CARD_INFO = new DDCSCLAC_CARD_INFO[4];
    public String RLT_AC = " ";
    public String RLT_AC_NAME = " ";
    public String RLT_BANK = " ";
    public String RLT_REF_NO = " ";
    public String RLT_CCY = " ";
    public String LOSS_NO = " ";
    public char PAY_TYPE = ' ';
    public String PAY_ID_TYPE = " ";
    public String PAY_ID_NO = " ";
    public String RVS_NO = " ";
    public char BAT_FLG = ' ';
    public String TRF_CCY = " ";
    public char TRF_CCY_TYPE = ' ';
    public String REG_CD = " ";
    public char CLVR_FLG = ' ';
    public String HOLDER_IDTYP = " ";
    public String HOLDER_IDNO = " ";
    public String HOLDER_CINO = " ";
    public String HOLDER_NAME = " ";
    public DDCSCLAC() {
        for (int i=0;i<4;i++) CARD_INFO[i] = new DDCSCLAC_CARD_INFO();
    }
}
