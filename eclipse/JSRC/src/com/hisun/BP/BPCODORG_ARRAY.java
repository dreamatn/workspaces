package com.hisun.BP;

public class BPCODORG_ARRAY {
    public String TLR = " ";
    public int TLR_SOFF_DT = 0;
    public int TLR_SOFF_TM = 0;
    public String PLBOX_NO = " ";
    public int CNT2 = 0;
    public BPCODORG_ARRAY1[] ARRAY1 = new BPCODORG_ARRAY1[10];
    public BPCODORG_ARRAY() {
        for (int i=0;i<10;i++) ARRAY1[i] = new BPCODORG_ARRAY1();
    }
}
