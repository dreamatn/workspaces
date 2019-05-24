package com.hisun.BP;

public class BPZSCPRI_WS_AUTH_ROLE_TXT {
    short WS_AUTH_ROLE_CNT = 0;
    BPZSCPRI_WS_AUTH_ROLE_INFO[] WS_AUTH_ROLE_INFO = new BPZSCPRI_WS_AUTH_ROLE_INFO[120];
    public BPZSCPRI_WS_AUTH_ROLE_TXT() {
        for (int i=0;i<120;i++) WS_AUTH_ROLE_INFO[i] = new BPZSCPRI_WS_AUTH_ROLE_INFO();
    }
}
