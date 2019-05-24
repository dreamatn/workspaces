package com.hisun.BP;

public class BPCOBOXD_DATA {
    public BPCOBOXD_PLAN[] PLAN = new BPCOBOXD_PLAN[60];
    public BPCOBOXD_DATA() {
        for (int i=0;i<60;i++) PLAN[i] = new BPCOBOXD_PLAN();
    }
}
