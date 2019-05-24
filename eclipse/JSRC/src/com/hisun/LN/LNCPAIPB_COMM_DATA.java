package com.hisun.LN;

public class LNCPAIPB_COMM_DATA {
    public String LN_AC = " ";
    public String SUF_NO = " ";
    public int PHS_CNT = 0;
    public LNCPAIPB_PHS_DATA[] PHS_DATA = new LNCPAIPB_PHS_DATA[20];
    public LNCPAIPB_COMM_DATA() {
        for (int i=0;i<20;i++) PHS_DATA[i] = new LNCPAIPB_PHS_DATA();
    }
}
