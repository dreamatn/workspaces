package com.hisun.TD;

public class TDCOFQFX {
    public int TOT_CNT = 0;
    public double TOT_BAL = 0;
    public char FILLER3 = 0X01;
    public double TOT_LINT = 0;
    public char FILLER5 = 0X01;
    public TDCOFQFX_AC_DATA[] AC_DATA = new TDCOFQFX_AC_DATA[50];
    public TDCOFQFX() {
        for (int i=0;i<50;i++) AC_DATA[i] = new TDCOFQFX_AC_DATA();
    }
}
