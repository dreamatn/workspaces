package com.hisun.BP;

public class BPRCWA_TXT {
    public BPRCWA_FLOW[] FLOW = new BPRCWA_FLOW[120];
    public BPRCWA_AP_TXT AP_TXT = new BPRCWA_AP_TXT();
    public BPRCWA_TXT() {
        for (int i=0;i<120;i++) FLOW[i] = new BPRCWA_FLOW();
    }
}
