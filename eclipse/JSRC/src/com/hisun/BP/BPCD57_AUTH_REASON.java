package com.hisun.BP;

public class BPCD57_AUTH_REASON {
    public BPCD57_AUTH_RESN_TBL[] AUTH_RESN_TBL = new BPCD57_AUTH_RESN_TBL[30];
    public BPCD57_AUTH_REASON() {
        for (int i=0;i<30;i++) AUTH_RESN_TBL[i] = new BPCD57_AUTH_RESN_TBL();
    }
}
