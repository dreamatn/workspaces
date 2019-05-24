package com.hisun.CM;

public class CMB1400_AWA_1400 {
    public String CHN_TYP = " ";
    public short CHN_TYP_NO = 0;
    public CMB1400_AC_DATA[] AC_DATA = new CMB1400_AC_DATA[40];
    public CMB1400_OUT_AC_D[] OUT_AC_D = new CMB1400_OUT_AC_D[2];
    public CMB1400_FEE_DATA[] FEE_DATA = new CMB1400_FEE_DATA[5];
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public CMB1400_AWA_1400() {
        for (int i=0;i<40;i++) AC_DATA[i] = new CMB1400_AC_DATA();
        for (int i=0;i<2;i++) OUT_AC_D[i] = new CMB1400_OUT_AC_D();
        for (int i=0;i<5;i++) FEE_DATA[i] = new CMB1400_FEE_DATA();
    }
}
