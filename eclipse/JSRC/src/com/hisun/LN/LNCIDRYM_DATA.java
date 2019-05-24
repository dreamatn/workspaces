package com.hisun.LN;

public class LNCIDRYM_DATA {
    public String T_C_NO = " ";
    public String CONTRACT_NO = " ";
    public int SUB_CTA_NO = 0;
    public String TXN_TYPE = " ";
    public double TXN_AMT = 0;
    public double TXN_AMT_I = 0;
    public String TXN_CCY = " ";
    public double CHANGE_AMT = 0;
    public int VAL_DATE = 0;
    public int MAT_DATE = 0;
    public long JRN_NO = 0;
    public int VAL_DATE_OLD = 0;
    public int MAT_DATE_OLD = 0;
    public char FULLPAY_IND = ' ';
    public char SCH_TYPE = ' ';
    public String SCH_P_TERM_NO = " ";
    public int SCH_P_VAL_DT = 0;
    public int SCH_P_DUE_DT = 0;
    public String SCH_I_TERM_NO = " ";
    public int SCH_I_VAL_DT = 0;
    public int SCH_I_DUE_DT = 0;
    public char ROV_TYPE = ' ';
    public LNCIDRYM_CTA[] CTA = new LNCIDRYM_CTA[5];
    public double OLD_CTA_TOT_AMT = 0;
    public double NEW_CTA_TOT_AMT = 0;
    public char ROV_IND = ' ';
    public int AVAIL_START_OLD = 0;
    public int AVAIL_END_OLD = 0;
    public LNCIDRYM_OS_BAL[] OS_BAL = new LNCIDRYM_OS_BAL[5];
    public String FILLER84 = " ";
    public LNCIDRYM_DATA() {
        for (int i=0;i<5;i++) CTA[i] = new LNCIDRYM_CTA();
        for (int i=0;i<5;i++) OS_BAL[i] = new LNCIDRYM_OS_BAL();
    }
}
