package com.hisun.CM;

public class CMB8060_AWA_8060 {
    public short AC_CNT = 0;
    public short AC_CNT_NO = 0;
    public CMB8060_ARRAY[] ARRAY = new CMB8060_ARRAY[10];
    public CMB8060_AWA_8060() {
        for (int i=0;i<10;i++) ARRAY[i] = new CMB8060_ARRAY();
    }
}
