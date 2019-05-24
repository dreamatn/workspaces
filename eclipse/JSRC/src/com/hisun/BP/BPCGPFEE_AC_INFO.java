package com.hisun.BP;

public class BPCGPFEE_AC_INFO {
    public short AC_CNT = 0;
    public BPCGPFEE_AC_INFO1[] AC_INFO1 = new BPCGPFEE_AC_INFO1[5];
    public BPCGPFEE_AC_INFO() {
        for (int i=0;i<5;i++) AC_INFO1[i] = new BPCGPFEE_AC_INFO1();
    }
}
