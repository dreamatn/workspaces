package com.hisun.CM;

public class CMB8030_AWA_8030 {
    public short AC_CNT = 0;
    public short AC_CNT_NO = 0;
    public CMB8030_ARRAY[] ARRAY = new CMB8030_ARRAY[10];
    public CMB8030_AWA_8030() {
        for (int i=0;i<10;i++) ARRAY[i] = new CMB8030_ARRAY();
    }
}
