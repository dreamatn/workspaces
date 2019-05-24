package com.hisun.DD;

public class DDCSQAC_OUTPUT_DATA {
    public DDCSQAC_BV_INFO BV_INFO = new DDCSQAC_BV_INFO();
    public DDCSQAC_ACAC_INFO[] ACAC_INFO = new DDCSQAC_ACAC_INFO[100];
    public DDCSQAC_OUTPUT_DATA() {
        for (int i=0;i<100;i++) ACAC_INFO[i] = new DDCSQAC_ACAC_INFO();
    }
}
