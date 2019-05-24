package com.hisun.CI;

public class CICCASHE {
    public short COUNT = 0;
    public CICCASHE_DATA[] DATA = new CICCASHE_DATA[2];
    public CICCASHE() {
        for (int i=0;i<2;i++) DATA[i] = new CICCASHE_DATA();
    }
}
