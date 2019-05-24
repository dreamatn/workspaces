package com.hisun.CM;

public class CMCS1400 {
    public String CHN_TYP = " ";
    public CMCS1400_AC_DATA[] AC_DATA = new CMCS1400_AC_DATA[40];
    public CMCS1400_OUT_AC_D[] OUT_AC_D = new CMCS1400_OUT_AC_D[2];
    public CMCS1400_FEE_DATA[] FEE_DATA = new CMCS1400_FEE_DATA[5];
    public String REMARK = " ";
    public CMCS1400() {
        for (int i=0;i<40;i++) AC_DATA[i] = new CMCS1400_AC_DATA();
        for (int i=0;i<2;i++) OUT_AC_D[i] = new CMCS1400_OUT_AC_D();
        for (int i=0;i<5;i++) FEE_DATA[i] = new CMCS1400_FEE_DATA();
    }
}
