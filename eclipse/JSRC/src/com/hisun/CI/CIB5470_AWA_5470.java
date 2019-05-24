package com.hisun.CI;

public class CIB5470_AWA_5470 {
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public CIB5470_AC_GRPS[] AC_GRPS = new CIB5470_AC_GRPS[10];
    public CIB5470_AWA_5470() {
        for (int i=0;i<10;i++) AC_GRPS[i] = new CIB5470_AC_GRPS();
    }
}
