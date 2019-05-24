package com.hisun.CM;

public class CMCF130 {
    public short CNT = 0;
    public CMCF130_ARRAY[] ARRAY = new CMCF130_ARRAY[400];
    public CMCF130() {
        for (int i=0;i<400;i++) ARRAY[i] = new CMCF130_ARRAY();
    }
}
