package com.hisun.GD;

public class GDB8310_AWA_8310 {
    public short AC_COUNT = 0;
    public short AC_COUNT_NO = 0;
    public GDB8310_AC_CNT[] AC_CNT = new GDB8310_AC_CNT[25];
    public GDB8310_AWA_8310() {
        for (int i=0;i<25;i++) AC_CNT[i] = new GDB8310_AC_CNT();
    }
}
