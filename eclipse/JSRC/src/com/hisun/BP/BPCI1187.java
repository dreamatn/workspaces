package com.hisun.BP;

public class BPCI1187 {
    public String CHNL = " ";
    public String RGN_NO = " ";
    public String PRD_CODE = " ";
    public String AC_NO = " ";
    public String CI_NO = " ";
    public String FFEE_CCY = " ";
    public short ACC_CNT = 0;
    public String CHG_CCY = " ";
    public double CHG_AMT = 0;
    public char AC_TYP = ' ';
    public String FEE_AC = " ";
    public String FEE_ADVI = " ";
    public long ACCT_CD = 0;
    public int DR_BANK = 0;
    public String RMK = " ";
    public String CFEE_CCY = " ";
    public int BR = 0;
    public String REMK = " ";
    public String TICKET = " ";
    public double RATE = 0;
    public double FEE_BAS = 0;
    public double ADJ_AMT = 0;
    public int EXG_DATE = 0;
    public int EXG_TIME = 0;
    public String C_P_NO = " ";
    public String CHQ_NO = " ";
    public int SALE_DT = 0;
    public String FEE_CTRT = " ";
    public int FEE_DPTM = 0;
    public String FEE_CONT = " ";
    public String FEE_ADV1 = " ";
    public char CHG_MOD = ' ';
    public BPCI1187_FEE_INF[] FEE_INF = new BPCI1187_FEE_INF[20];
    public char CCY_TYPE = ' ';
    public String BSNS_NO = " ";
    public String CREV_NO = " ";
    public double TOT_AMT = 0;
    public String SVR_CD = " ";
    public int CR_BANK = 0;
    public int VAL_DT = 0;
    public char CHG_FLG = ' ';
    public String FREE_FMT = " ";
    public BPCI1187() {
        for (int i=0;i<20;i++) FEE_INF[i] = new BPCI1187_FEE_INF();
    }
}
