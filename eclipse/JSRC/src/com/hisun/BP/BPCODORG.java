package com.hisun.BP;

public class BPCODORG {
    public int BR = 0;
    public int CNT = 0;
    public int ORG_CLO_DT = 0;
    public BPCODORG_ARRAY[] ARRAY = new BPCODORG_ARRAY[40];
    public BPCODORG() {
        for (int i=0;i<40;i++) ARRAY[i] = new BPCODORG_ARRAY();
    }
}
