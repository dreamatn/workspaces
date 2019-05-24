package com.hisun.LN;

public class LNCSRPNR_COMM_DATA {
    public int TXN_DT = 0;
    public long JRNNO = 0;
    public String LN_AC = " ";
    public int SUF_NO = 0;
    public double MAX_PAY_AMT = 0;
    public char W_LC_IND = ' ';
    public char W_INT_IND = ' ';
    public double W_INT_AMT = 0;
    public int TR_VAL_DATE = 0;
    public String DR_AC = " ";
    public LNCSRPNR_TOT_AMTS TOT_AMTS = new LNCSRPNR_TOT_AMTS();
    public LNCSRPNR_TOT_D_AMTS TOT_D_AMTS = new LNCSRPNR_TOT_D_AMTS();
    public LNCSRPNR_RCV_DATAS RCV_DATAS = new LNCSRPNR_RCV_DATAS();
}
