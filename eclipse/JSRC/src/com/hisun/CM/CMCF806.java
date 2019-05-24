package com.hisun.CM;

public class CMCF806 {
    public short AC_CNT = 0;
    public CMCF806_ACNO_ARRAY[] ACNO_ARRAY = new CMCF806_ACNO_ARRAY[10];
    public CMCF806() {
        for (int i=0;i<10;i++) ACNO_ARRAY[i] = new CMCF806_ACNO_ARRAY();
    }
}
