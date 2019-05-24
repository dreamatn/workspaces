package com.hisun.LN;

public class LNCI3200 {
    public char FUNC = ' ';
    public String CTA_NO = " ";
    public long TRAN_SEQ = 0;
    public char CONF_FLG = ' ';
    public LNCI3200_MRS_DATA[] MRS_DATA = new LNCI3200_MRS_DATA[20];
    public LNCI3200() {
        for (int i=0;i<20;i++) MRS_DATA[i] = new LNCI3200_MRS_DATA();
    }
}
