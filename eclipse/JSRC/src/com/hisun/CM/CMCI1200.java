package com.hisun.CM;

public class CMCI1200 {
    public String CHN_TYP = " ";
    public CMCI1200_AC_DATA[] AC_DATA = new CMCI1200_AC_DATA[5];
    public CMCI1200_EVE_DATA[] EVE_DATA = new CMCI1200_EVE_DATA[5];
    public CMCI1200_FEE_DATA[] FEE_DATA = new CMCI1200_FEE_DATA[5];
    public String RMK = " ";
    public CMCI1200() {
        for (int i=0;i<5;i++) AC_DATA[i] = new CMCI1200_AC_DATA();
        for (int i=0;i<5;i++) EVE_DATA[i] = new CMCI1200_EVE_DATA();
        for (int i=0;i<5;i++) FEE_DATA[i] = new CMCI1200_FEE_DATA();
    }
}
