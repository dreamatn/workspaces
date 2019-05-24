package com.hisun.CM;

public class CMCF803 {
    public short AC_CNT = 0;
    public CMCF803_ACNO_ARRAY[] ACNO_ARRAY = new CMCF803_ACNO_ARRAY[10];
    public CMCF803() {
        for (int i=0;i<10;i++) ACNO_ARRAY[i] = new CMCF803_ACNO_ARRAY();
    }
}
