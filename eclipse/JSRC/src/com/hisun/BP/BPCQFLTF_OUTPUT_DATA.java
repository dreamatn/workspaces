package com.hisun.BP;

public class BPCQFLTF_OUTPUT_DATA {
    public short FLT_CNT = 0;
    public BPCQFLTF_DATA[] DATA = new BPCQFLTF_DATA[100];
    public BPCQFLTF_OUTPUT_DATA() {
        for (int i=0;i<100;i++) DATA[i] = new BPCQFLTF_DATA();
    }
}
