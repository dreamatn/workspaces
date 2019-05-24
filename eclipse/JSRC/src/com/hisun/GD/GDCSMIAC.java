package com.hisun.GD;

public class GDCSMIAC {
    public short AC_COUNT = 0;
    public GDCSMIAC_AC_INFO[] AC_INFO = new GDCSMIAC_AC_INFO[25];
    public GDCSMIAC() {
        for (int i=0;i<25;i++) AC_INFO[i] = new GDCSMIAC_AC_INFO();
    }
}
