package com.hisun.BP;

public class BPCIPHIS_REC_DATA {
    public BPCIPHIS_RETURN_DATA[] RETURN_DATA = new BPCIPHIS_RETURN_DATA[10];
    public BPCIPHIS_REC_DATA() {
        for (int i=0;i<10;i++) RETURN_DATA[i] = new BPCIPHIS_RETURN_DATA();
    }
}
