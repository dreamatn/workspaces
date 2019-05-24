package com.hisun.BP;

public class BPCCINTI_EXT_INFO {
    public int REF_CNT = 0;
    public BPCCINTI_REF_TBL[] REF_TBL = new BPCCINTI_REF_TBL[10];
    public int REF_DEPTH = 0;
    public BPCCINTI_EXT_INFO() {
        for (int i=0;i<10;i++) REF_TBL[i] = new BPCCINTI_REF_TBL();
    }
}
