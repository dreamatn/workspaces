package com.hisun.BP;

public class BPCODERO_INFO {
    public BPCODERO_DATA[] DATA = new BPCODERO_DATA[20];
    public BPCODERO_INFO() {
        for (int i=0;i<20;i++) DATA[i] = new BPCODERO_DATA();
    }
}
