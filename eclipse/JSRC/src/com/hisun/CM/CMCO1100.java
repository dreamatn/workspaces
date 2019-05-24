package com.hisun.CM;

public class CMCO1100 {
    public String CHN_TYP = " ";
    public CMCO1100_AC_DATA[] AC_DATA = new CMCO1100_AC_DATA[2];
    public CMCO1100_MIB_DATA[] MIB_DATA = new CMCO1100_MIB_DATA[10];
    public CMCO1100_FEE_DATA[] FEE_DATA = new CMCO1100_FEE_DATA[5];
    public CMCO1100() {
        for (int i=0;i<2;i++) AC_DATA[i] = new CMCO1100_AC_DATA();
        for (int i=0;i<10;i++) MIB_DATA[i] = new CMCO1100_MIB_DATA();
        for (int i=0;i<5;i++) FEE_DATA[i] = new CMCO1100_FEE_DATA();
    }
}
