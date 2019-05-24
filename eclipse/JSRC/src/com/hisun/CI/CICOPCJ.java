package com.hisun.CI;

public class CICOPCJ {
    public CICOPCJ_RC RC = new CICOPCJ_RC();
    public CICOPCJ_DATA[] DATA = new CICOPCJ_DATA[10];
    public CICOPCJ() {
        for (int i=0;i<10;i++) DATA[i] = new CICOPCJ_DATA();
    }
}
