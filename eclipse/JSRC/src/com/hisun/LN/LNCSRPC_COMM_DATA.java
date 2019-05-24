package com.hisun.LN;

public class LNCSRPC_COMM_DATA {
    public String CTA_NO = " ";
    public int TR_VAL_DTE = 0;
    public double TOT_AMT = 0;
    public double TOT_DTL_AMT = 0;
    public double TOT_P_AMT = 0;
    public double TOT_I_AMT = 0;
    public double TOT_O_AMT = 0;
    public double TOT_L_AMT = 0;
    public double TOT_P_UDAMT = 0;
    public double TOT_I_UDAMT = 0;
    public String APT_REF = " ";
    public double RDI_AMT = 0;
    public double HRG_AMT = 0;
    public char SUBS_FLG = ' ';
    public char CUR_TRM = ' ';
    public LNCSRPC_PART_DATA[] PART_DATA = new LNCSRPC_PART_DATA[10];
    public LNCSRPC_COMM_DATA() {
        for (int i=0;i<10;i++) PART_DATA[i] = new LNCSRPC_PART_DATA();
    }
}
