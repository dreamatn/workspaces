package com.hisun.LN;

public class LNCURPNR_COMM_DATA {
    public int TXN_DT = 0;
    public long JRNNO = 0;
    public String ACM_EVENT = " ";
    public String LN_AC = " ";
    public String SUF_NO = " ";
    public int TR_VAL_DATE = 0;
    public LNCURPNR_TOT_D_AMTS TOT_D_AMTS = new LNCURPNR_TOT_D_AMTS();
    public LNCURPNR_B_AMTS B_AMTS = new LNCURPNR_B_AMTS();
    public LNCURPNR_R_AMTS R_AMTS = new LNCURPNR_R_AMTS();
    public char SUBS_FLG = ' ';
    public char RDI_FLG = ' ';
    public double RDI_AMT = 0;
    public char ADJ_TYP = ' ';
    public String ADJ_AC = " ";
    public double HRG_AMT = 0;
    public char CUR_TRM = ' ';
    public char CLN_CUT = ' ';
    public String CTL_STSW = " ";
}
