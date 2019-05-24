package com.hisun.CM;

public class CMCO1600 {
    public String CHN_TYP = " ";
    public CMCO1600_AC_DATA[] AC_DATA = new CMCO1600_AC_DATA[6];
    public CMCO1600_MIB_DATA[] MIB_DATA = new CMCO1600_MIB_DATA[10];
    public CMCO1600_FEE_DATA[] FEE_DATA = new CMCO1600_FEE_DATA[5];
    public CMCO1600() {
        for (int i=0;i<6;i++) AC_DATA[i] = new CMCO1600_AC_DATA();
        for (int i=0;i<10;i++) MIB_DATA[i] = new CMCO1600_MIB_DATA();
        for (int i=0;i<5;i++) FEE_DATA[i] = new CMCO1600_FEE_DATA();
    }
}
