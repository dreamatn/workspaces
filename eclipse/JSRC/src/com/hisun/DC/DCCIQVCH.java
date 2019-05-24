package com.hisun.DC;

public class DCCIQVCH {
    public DCCIQVCH_RC RC = new DCCIQVCH_RC();
    public DCCIQVCH_INPUT INPUT = new DCCIQVCH_INPUT();
    public DCCIQVCH_OUTPUT[] OUTPUT = new DCCIQVCH_OUTPUT[100];
    public DCCIQVCH() {
        for (int i=0;i<100;i++) OUTPUT[i] = new DCCIQVCH_OUTPUT();
    }
}
