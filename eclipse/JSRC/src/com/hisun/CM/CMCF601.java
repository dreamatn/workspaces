package com.hisun.CM;

public class CMCF601 {
    public int REC_CNT = 0;
    public CMCF601_ARRAY[] ARRAY = new CMCF601_ARRAY[10];
    public CMCF601() {
        for (int i=0;i<10;i++) ARRAY[i] = new CMCF601_ARRAY();
    }
}
