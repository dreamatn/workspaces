package com.hisun.CM;

public class CMB8010_AWA_8010 {
    public short AC_CNT = 0;
    public short AC_CNT_NO = 0;
    public CMB8010_AC_ARY[] AC_ARY = new CMB8010_AC_ARY[80];
    public CMB8010_AWA_8010() {
        for (int i=0;i<80;i++) AC_ARY[i] = new CMB8010_AC_ARY();
    }
}
