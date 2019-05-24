package com.hisun.BP;

public class BPRANS_ITM {
    public BPRANS_CD[] CD = new BPRANS_CD[10];
    public BPRANS_ITM() {
        for (int i=0;i<10;i++) CD[i] = new BPRANS_CD();
    }
}
