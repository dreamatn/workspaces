package com.hisun.GD;

public class GDB1600_AWA_1600 {
    public short CNT = 0;
    public short CNT_NO = 0;
    public GDB1600_ARAY_CNT[] ARAY_CNT = new GDB1600_ARAY_CNT[5];
    public GDB1600_AWA_1600() {
        for (int i=0;i<5;i++) ARAY_CNT[i] = new GDB1600_ARAY_CNT();
    }
}
