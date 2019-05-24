package com.hisun.DD;

public class DDCIMCCY {
    public DDCIMCCY_RC RC = new DDCIMCCY_RC();
    public DDCIMCCY_DATA[] DATA = new DDCIMCCY_DATA[40];
    public DDCIMCCY() {
        for (int i=0;i<40;i++) DATA[i] = new DDCIMCCY_DATA();
    }
}
