package com.hisun.CI;

public class CICMCNT_MULTI_DATA {
    public CICMCNT_M_DATA[] M_DATA = new CICMCNT_M_DATA[5];
    public CICMCNT_MULTI_DATA() {
        for (int i=0;i<5;i++) M_DATA[i] = new CICMCNT_M_DATA();
    }
}
