package com.hisun.CM;

public class CMCF160 {
    public int REC_CNT = 0;
    public CMCF160_ARRAY[] ARRAY = new CMCF160_ARRAY[10];
    public CMCF160() {
        for (int i=0;i<10;i++) ARRAY[i] = new CMCF160_ARRAY();
    }
}
