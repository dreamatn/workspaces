package com.hisun.CI;

public class CICOACLB {
    public short COUNT = 0;
    public CICOACLB_DATA[] DATA = new CICOACLB_DATA[300];
    public CICOACLB() {
        for (int i=0;i<300;i++) DATA[i] = new CICOACLB_DATA();
    }
}
