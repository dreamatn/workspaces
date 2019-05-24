package com.hisun.LN;

public class LNCURPN_COMM_DATA {
    public String ACM_EVENT = " ";
    public String LN_AC = " ";
    public String SUF_NO = " ";
    public int TR_VAL_DATE = 0;
    public String APT_REF = " ";
    public LNCURPN_TOT_D_AMTS TOT_D_AMTS = new LNCURPN_TOT_D_AMTS();
    public LNCURPN_B_AMTS B_AMTS = new LNCURPN_B_AMTS();
    public LNCURPN_R_AMTS R_AMTS = new LNCURPN_R_AMTS();
    public char SUBS_FLG = ' ';
    public char RDI_FLG = ' ';
    public double RDI_AMT = 0;
    public char ADJ_TYP = ' ';
    public String ADJ_AC = " ";
    public double HRG_AMT = 0;
    public char CUR_TRM = ' ';
    public double INSYS_EXPEND_INT = 0;
    public double OUTSYS_EXPEND_INT = 0;
    public double REDISC_EXPEND_INT = 0;
    public String MMO = " ";
    public LNCURPN_ACAMT[] ACAMT = new LNCURPN_ACAMT[5];
    public char CLN_CUT = ' ';
    public String CTL_STSW = " ";
    public LNCURPN_COMM_DATA() {
        for (int i=0;i<5;i++) ACAMT[i] = new LNCURPN_ACAMT();
    }
}
