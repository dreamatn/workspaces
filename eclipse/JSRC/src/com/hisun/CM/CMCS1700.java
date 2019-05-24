package com.hisun.CM;

public class CMCS1700 {
    public CMCS1700_AC_DATA[] AC_DATA = new CMCS1700_AC_DATA[2];
    public String REMARK = " ";
    public CMCS1700() {
        for (int i=0;i<2;i++) AC_DATA[i] = new CMCS1700_AC_DATA();
    }
}
