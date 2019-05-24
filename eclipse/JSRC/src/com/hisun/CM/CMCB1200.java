package com.hisun.CM;

public class CMCB1200 {
    public int CNT = 0;
    public CMCB1200_ARRAY[] ARRAY = new CMCB1200_ARRAY[99];
    public CMCB1200() {
        for (int i=0;i<99;i++) ARRAY[i] = new CMCB1200_ARRAY();
    }
}
