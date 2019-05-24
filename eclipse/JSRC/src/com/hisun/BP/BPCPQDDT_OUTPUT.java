package com.hisun.BP;

public class BPCPQDDT_OUTPUT {
    public int CNT = 0;
    public BPCPQDDT_RN_DATA[] RN_DATA = new BPCPQDDT_RN_DATA[100];
    public BPCPQDDT_OUTPUT() {
        for (int i=0;i<100;i++) RN_DATA[i] = new BPCPQDDT_RN_DATA();
    }
}
