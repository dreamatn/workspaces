package com.hisun.LN;

public class LNEE_FEE_DATA {
    char SEND_FLG = ' ';
    String EXP_CODE = " ";
    short FEE_CNT = 0;
    LNEE_FEE_INFO[] FEE_INFO = new LNEE_FEE_INFO[20];
    int ACCOUNT_BR = 0;
    double EX_RATE = 0;
    String TICKET_NO = " ";
    String REMARK = " ";
    String FILLER37 = " ";
    public LNEE_FEE_DATA() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new LNEE_FEE_INFO();
    }
}
