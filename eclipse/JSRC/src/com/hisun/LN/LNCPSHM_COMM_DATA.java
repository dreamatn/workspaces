package com.hisun.LN;

public class LNCPSHM_COMM_DATA {
    public String LN_AC = " ";
    public String SUF_NO = " ";
    public char TX_TYP = ' ';
    public char PAY_TYP = ' ';
    public short START_TE = 0;
    public short STRIN_TE = 0;
    public short TOTAL_TE = 0;
    public LNCPSHM_TERMINFO[] TERMINFO = new LNCPSHM_TERMINFO[20];
    public LNCPSHM_COMM_DATA() {
        for (int i=0;i<20;i++) TERMINFO[i] = new LNCPSHM_TERMINFO();
    }
}
