package com.hisun.CM;

public class CMCO8860 {
    public String CHN_TYP = " ";
    public CMCO8860_AC_DATA[] AC_DATA = new CMCO8860_AC_DATA[2];
    public CMCO8860_MIB_DATA[] MIB_DATA = new CMCO8860_MIB_DATA[10];
    public CMCO8860() {
        for (int i=0;i<2;i++) AC_DATA[i] = new CMCO8860_AC_DATA();
        for (int i=0;i<10;i++) MIB_DATA[i] = new CMCO8860_MIB_DATA();
    }
}
