package com.hisun.BP;

public class BPCORTID {
    public String B_TYPE = " ";
    public BPCORTID_TENOR_DATA[] TENOR_DATA = new BPCORTID_TENOR_DATA[20];
    public int DATA_CNT = 0;
    public BPCORTID() {
        for (int i=0;i<20;i++) TENOR_DATA[i] = new BPCORTID_TENOR_DATA();
    }
}
