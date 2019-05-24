package com.hisun.BP;

public class BPCOQACD {
    public BPCOQACD_RC RC = new BPCOQACD_RC();
    public String TXN_CODE = " ";
    public int CNT = 0;
    public BPCOQACD_DATA[] DATA = new BPCOQACD_DATA[10];
    public BPCOQACD() {
        for (int i=0;i<10;i++) DATA[i] = new BPCOQACD_DATA();
    }
}
