package com.hisun.CM;

public class CMB6010_AWA_6010 {
    public int REC_CNT = 0;
    public short REC_CNT_NO = 0;
    public CMB6010_ARRAY[] ARRAY = new CMB6010_ARRAY[10];
    public CMB6010_AWA_6010() {
        for (int i=0;i<10;i++) ARRAY[i] = new CMB6010_ARRAY();
    }
}
