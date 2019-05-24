package com.hisun.CM;

public class CMCO1200 {
    public String CHN_TYP = " ";
    public CMCO1200_AC_DATA[] AC_DATA = new CMCO1200_AC_DATA[5];
    public CMCO1200_EVE_DATA[] EVE_DATA = new CMCO1200_EVE_DATA[5];
    public CMCO1200_FEE_DATA[] FEE_DATA = new CMCO1200_FEE_DATA[5];
    public CMCO1200() {
        for (int i=0;i<5;i++) AC_DATA[i] = new CMCO1200_AC_DATA();
        for (int i=0;i<5;i++) EVE_DATA[i] = new CMCO1200_EVE_DATA();
        for (int i=0;i<5;i++) FEE_DATA[i] = new CMCO1200_FEE_DATA();
    }
}
