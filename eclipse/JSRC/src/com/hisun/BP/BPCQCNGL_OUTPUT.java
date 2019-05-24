package com.hisun.BP;

public class BPCQCNGL_OUTPUT {
    public String MOD_NO = " ";
    public BPCQCNGL_MSTNO_ARRAY[] MSTNO_ARRAY = new BPCQCNGL_MSTNO_ARRAY[10];
    public String OTH = " ";
    public BPCQCNGL_OUTPUT() {
        for (int i=0;i<10;i++) MSTNO_ARRAY[i] = new BPCQCNGL_MSTNO_ARRAY();
    }
}
