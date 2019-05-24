package com.hisun.BP;

public class BPCSBOXM {
    public BPCSBOXM_RC RC = new BPCSBOXM_RC();
    public char FUNC = ' ';
    public String OUTPUT_FMT = " ";
    public int PLAN_DT = 0;
    public int BR = 0;
    public BPCSBOXM_PLAN_INFO[] PLAN_INFO = new BPCSBOXM_PLAN_INFO[60];
    public BPCSBOXM() {
        for (int i=0;i<60;i++) PLAN_INFO[i] = new BPCSBOXM_PLAN_INFO();
    }
}
