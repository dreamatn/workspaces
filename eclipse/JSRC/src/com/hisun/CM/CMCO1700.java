package com.hisun.CM;

public class CMCO1700 {
    public CMCO1700_AC_DATA[] AC_DATA = new CMCO1700_AC_DATA[2];
    public CMCO1700() {
        for (int i=0;i<2;i++) AC_DATA[i] = new CMCO1700_AC_DATA();
    }
}
