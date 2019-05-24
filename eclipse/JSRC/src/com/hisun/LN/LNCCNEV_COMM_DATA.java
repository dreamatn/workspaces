package com.hisun.LN;

public class LNCCNEV_COMM_DATA {
    public String EVENT_CODE = " ";
    public String ACM_EVENT = " ";
    public String LN_AC = " ";
    public String SUF_NO = " ";
    public int VAL_DATE = 0;
    public double P_AMT = 0;
    public double I_AMT = 0;
    public double O_AMT = 0;
    public double L_AMT = 0;
    public double F_AMT = 0;
    public LNCCNEV_IETM_AMTS[] IETM_AMTS = new LNCCNEV_IETM_AMTS[5];
    public char RVS_IND = ' ';
    public double NX_I_AMT = 0;
    public double NX_OLC_AMT = 0;
    public double NX_LLC_AMT = 0;
    public double TX_I_AMT = 0;
    public double TX_OLC_AMT = 0;
    public double TX_LLC_AMT = 0;
    public LNCCNEV_COMM_DATA() {
        for (int i=0;i<5;i++) IETM_AMTS[i] = new LNCCNEV_IETM_AMTS();
    }
}
