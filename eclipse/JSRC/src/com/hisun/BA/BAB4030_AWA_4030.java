package com.hisun.BA;

public class BAB4030_AWA_4030 {
    public String ID_NO = " ";
    public short ID_NO_NO = 0;
    public short FEE_CNT = 0;
    public short FEE_CNT_NO = 0;
    public BAB4030_FEE_DATA[] FEE_DATA = new BAB4030_FEE_DATA[5];
    public BAB4030_AWA_4030() {
        for (int i=0;i<5;i++) FEE_DATA[i] = new BAB4030_FEE_DATA();
    }
}
