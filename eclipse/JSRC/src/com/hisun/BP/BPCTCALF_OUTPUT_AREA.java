package com.hisun.BP;

public class BPCTCALF_OUTPUT_AREA {
    public BPCTCALF_FEE_INFO[] FEE_INFO = new BPCTCALF_FEE_INFO[20];
    public BPCTCALF_OUTPUT_AREA() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCTCALF_FEE_INFO();
    }
}
