package com.hisun.LN;

public class LNCI5218 {
    public String CTA_NO = " ";
    public long TRAN_SEQ = 0;
    public char CONF_FLG = ' ';
    public LNCI5218_MRS_DATA[] MRS_DATA = new LNCI5218_MRS_DATA[40];
    public LNCI5218() {
        for (int i=0;i<40;i++) MRS_DATA[i] = new LNCI5218_MRS_DATA();
    }
}
