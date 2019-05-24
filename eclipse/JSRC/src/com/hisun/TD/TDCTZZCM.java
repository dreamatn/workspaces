package com.hisun.TD;

public class TDCTZZCM {
    public String PROD_CD = " ";
    public String BV_CD = " ";
    public char BV_TYP = ' ';
    public char PRT_FLG = ' ';
    public String BV_NO = " ";
    public String MAIN_AC = " ";
    public String CARD_NO = " ";
    public short CVV = 0;
    public String ID_TYP = " ";
    public String ID_NO = " ";
    public String CI_NO = " ";
    public String NAME = " ";
    public String TEL = " ";
    public String ADDR = " ";
    public short OPN_CNT = 0;
    public String CCY = " ";
    public char CCY_TYP = ' ';
    public short FC_CD = 0;
    public char INSTR_MTH = ' ';
    public double TOTAL_AMT = 0;
    public char DRAW_MTH = ' ';
    public char CUS_PSW_FLG = ' ';
    public String PSW = " ";
    public int SEAL_NO = 0;
    public char CROS_DR_FLG = ' ';
    public int VAL_DT = 0;
    public TDCTZZCM_EXCL_INF1[] EXCL_INF1 = new TDCTZZCM_EXCL_INF1[6];
    public char CT_FLG = ' ';
    public String OPP_AC = " ";
    public String OPP_BV_NO = " ";
    public String OPP_CARD_NO = " ";
    public short OPP_CVV = 0;
    public String OPP_NAME = " ";
    public String OPP_REV_NO = " ";
    public char OPP_DRAW_MTH = ' ';
    public String OPP_PSW = " ";
    public String OPP_ID_TYP = " ";
    public String OPP_ID_NO = " ";
    public int OPP_SEAL_NO = 0;
    public char CHQ_TYPE = ' ';
    public String CHQ_NO = " ";
    public int CHQ_DATE = 0;
    public String PAY_PSW = " ";
    public String ATTY_ID_TYP = " ";
    public String ATTY_ID_NO = " ";
    public String ATTY_NAME = " ";
    public String TXN_CHNL = " ";
    public char TXN_PNT = ' ';
    public TDCTZZCM_EXCL_INF2[] EXCL_INF2 = new TDCTZZCM_EXCL_INF2[6];
    public TDCTZZCM() {
        for (int i=0;i<6;i++) EXCL_INF1[i] = new TDCTZZCM_EXCL_INF1();
        for (int i=0;i<6;i++) EXCL_INF2[i] = new TDCTZZCM_EXCL_INF2();
    }
}
