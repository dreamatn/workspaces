package com.hisun.CM;

public class CMCO1400 {
    public CMCO1400_AC_DATA[] AC_DATA = new CMCO1400_AC_DATA[40];
    public CMCO1400() {
        for (int i=0;i<40;i++) AC_DATA[i] = new CMCO1400_AC_DATA();
    }
}
