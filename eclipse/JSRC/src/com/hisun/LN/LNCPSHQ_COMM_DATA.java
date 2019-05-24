package com.hisun.LN;

public class LNCPSHQ_COMM_DATA {
    public String CONTRACT_NO = " ";
    public int SUB_CTA_NO = 0;
    public char PAY_TYP = ' ';
    public short START_TE = 0;
    public short STRIN_TE = 0;
    public short TOTAL_TE = 0;
    public LNCPSHQ_TERMINFO[] TERMINFO = new LNCPSHQ_TERMINFO[20];
    public LNCPSHQ_COMM_DATA() {
        for (int i=0;i<20;i++) TERMINFO[i] = new LNCPSHQ_TERMINFO();
    }
}
