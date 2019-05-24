package com.hisun.CM;

public class CMB1200_AWA_1200 {
    public String CHN_TYP = " ";
    public short CHN_TYP_NO = 0;
    public CMB1200_AC_DATA[] AC_DATA = new CMB1200_AC_DATA[5];
    public CMB1200_EVE_DATA[] EVE_DATA = new CMB1200_EVE_DATA[5];
    public CMB1200_FEE_DATA[] FEE_DATA = new CMB1200_FEE_DATA[5];
    public String RMK = " ";
    public short RMK_NO = 0;
    public CMB1200_AWA_1200() {
        for (int i=0;i<5;i++) AC_DATA[i] = new CMB1200_AC_DATA();
        for (int i=0;i<5;i++) EVE_DATA[i] = new CMB1200_EVE_DATA();
        for (int i=0;i<5;i++) FEE_DATA[i] = new CMB1200_FEE_DATA();
    }
}
