package com.hisun.LN;

public class LNCTMPPB_COMM_DATA {
    public long TRAN_SEQ = 0;
    public String LN_AC = " ";
    public String SUF_NO = " ";
    public int SEQ = 0;
    public int PHS_CNT = 0;
    public LNCTMPPB_PHS_DATA[] PHS_DATA = new LNCTMPPB_PHS_DATA[20];
    public LNCTMPPB_COMM_DATA() {
        for (int i=0;i<20;i++) PHS_DATA[i] = new LNCTMPPB_PHS_DATA();
    }
}
