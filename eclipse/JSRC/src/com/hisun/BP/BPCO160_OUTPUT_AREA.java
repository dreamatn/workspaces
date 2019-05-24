package com.hisun.BP;

public class BPCO160_OUTPUT_AREA {
    public BPCO160_FEE_INFO[] FEE_INFO = new BPCO160_FEE_INFO[20];
    public BPCO160_OUTPUT_AREA() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCO160_FEE_INFO();
    }
}
