package com.hisun.BP;

public class BPCQBRDS {
    public short NO = 0;
    public BPCQBRDS_ARRAY[] ARRAY = new BPCQBRDS_ARRAY[10];
    public BPCQBRDS() {
        for (int i=0;i<10;i++) ARRAY[i] = new BPCQBRDS_ARRAY();
    }
}
