package com.hisun.BP;

public class BPCOBOXQ_DATA {
    public BPCOBOXQ_PLAN[] PLAN = new BPCOBOXQ_PLAN[60];
    public BPCOBOXQ_DATA() {
        for (int i=0;i<60;i++) PLAN[i] = new BPCOBOXQ_PLAN();
    }
}
