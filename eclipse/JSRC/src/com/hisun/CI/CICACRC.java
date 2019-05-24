package com.hisun.CI;

public class CICACRC {
    public short COUNT = 0;
    public CICACRC_DATA[] DATA = new CICACRC_DATA[2];
    public CICACRC() {
        for (int i=0;i<2;i++) DATA[i] = new CICACRC_DATA();
    }
}
