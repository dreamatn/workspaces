package com.hisun.LN;

public class LNCSCLR_COMM_DATA {
    public char FUNC_CODE = ' ';
    public String CTA_NO = " ";
    public int TR_VAL_DT = 0;
    public char P_CLR_FLG = ' ';
    public char I_CLR_FLG = ' ';
    public char PC_CLR_FLG = ' ';
    public char CLN_CUT = ' ';
    public LNCSCLR_PAY_DATA[] PAY_DATA = new LNCSCLR_PAY_DATA[5];
    public double NOR_P = 0;
    public double NOR_I = 0;
    public double OVR_P = 0;
    public double OVR_I = 0;
    public double NOR_O = 0;
    public double NOR_L = 0;
    public double PC_FEE_AMT = 0;
    public double HRG_AMT = 0;
    public String FEE_ACT = " ";
    public String FEE_AC = " ";
    public LNCSCLR_FEE_INFO[] FEE_INFO = new LNCSCLR_FEE_INFO[5];
    public char CCY_TYP = ' ';
    public LNCSCLR_COMM_DATA() {
        for (int i=0;i<5;i++) PAY_DATA[i] = new LNCSCLR_PAY_DATA();
        for (int i=0;i<5;i++) FEE_INFO[i] = new LNCSCLR_FEE_INFO();
    }
}
