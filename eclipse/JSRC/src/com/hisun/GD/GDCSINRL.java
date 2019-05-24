package com.hisun.GD;

public class GDCSINRL {
    public char FUNC = ' ';
    public short CNT = 0;
    public GDCSINRL_ARAY_CNT[] ARAY_CNT = new GDCSINRL_ARAY_CNT[5];
    public GDCSINRL() {
        for (int i=0;i<5;i++) ARAY_CNT[i] = new GDCSINRL_ARAY_CNT();
    }
}
