package com.hisun.CI;

public class CICBASC {
    public short COUNT = 0;
    public CICBASC_DATA[] DATA = new CICBASC_DATA[2];
    public CICBASC() {
        for (int i=0;i<2;i++) DATA[i] = new CICBASC_DATA();
    }
}
