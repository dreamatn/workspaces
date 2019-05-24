package com.hisun.BP;

public class BPCI9133 {
    public String CTRT_NO = " ";
    public String FEE_DESC = " ";
    public String CI_NO = " ";
    public String AB_NAME = " ";
    public String FEE_TYPE = " ";
    public int BK_ACCT = 0;
    public String PROD_TYP = " ";
    public int STA_DATE = 0;
    public int MAT_DATE = 0;
    public char UCT_FLG = ' ';
    public char SET_FREQ = ' ';
    public short FREQ_CNT = 0;
    public int FIRST_DT = 0;
    public char HOL_OVER = ' ';
    public String CAL_CD1 = " ";
    public char HOL_METH = ' ';
    public String CAL_CD2 = " ";
    public char PAY_IND = ' ';
    public char CASH_IND = ' ';
    public String PORT_CD = " ";
    public char ACCR_TYP = ' ';
    public char PRICE_ME = ' ';
    public String TXN_CCY = " ";
    public double TXN_AMT = 0;
    public char REL_SRC = ' ';
    public String REL_COL = " ";
    public long REL_LMT = 0;
    public double FIR_DSCT = 0;
    public short PR_DAYS = 0;
    public String RCTRT_NO = " ";
    public String R_CT_TYP = " ";
    public short AMT_TYP = 0;
    public String R_PD_TYP = " ";
    public double MULTI = 0;
    public String INT_BAS = " ";
    public char AGGR_TYP = ' ';
    public String REF_CCY = " ";
    public char REF_MTH = ' ';
    public String FEE_CCY = " ";
    public BPCI9133_REF_DATE[] REF_DATE = new BPCI9133_REF_DATE[5];
    public char AUTO_FLG = ' ';
    public String CHG_CCY = " ";
    public double CHG_AMT = 0;
    public char CHG_MTH = ' ';
    public String CHG_AC = " ";
    public String NOSTR_CD = " ";
    public String CHQ_NO = " ";
    public int GL_MST1 = 0;
    public int GL_MST2 = 0;
    public int GL_MST3 = 0;
    public int GL_MST4 = 0;
    public BPCI9133_RES_DATE[] RES_DATE = new BPCI9133_RES_DATE[5];
    public char FEE_STS = ' ';
    public String RMK = " ";
    public double MIN_AMT = 0;
    public String CCY_REAL = " ";
    public String CP_NO = " ";
    public int SALE_DT = 0;
    public char CCY_TYPE = ' ';
    public String CREV_NO = " ";
    public BPCI9133() {
        for (int i=0;i<5;i++) REF_DATE[i] = new BPCI9133_REF_DATE();
        for (int i=0;i<5;i++) RES_DATE[i] = new BPCI9133_RES_DATE();
    }
}