package com.hisun.BP;

public class BPCDEGR_RULE_DATA {
    public BPCDEGR_DATA[] DATA = new BPCDEGR_DATA[20];
    public BPCDEGR_RULE_DATA() {
        for (int i=0;i<20;i++) DATA[i] = new BPCDEGR_DATA();
    }
}
