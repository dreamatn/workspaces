package com.hisun.BP;

public class BPCCPRL {
    public BPCCPRL_RC RC = new BPCCPRL_RC();
    public String CPNT_ID = " ";
    public BPCCPRL_DATA[] DATA = new BPCCPRL_DATA[20];
    public BPCCPRL() {
        for (int i=0;i<20;i++) DATA[i] = new BPCCPRL_DATA();
    }
}
