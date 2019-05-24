package com.hisun.BP;

public class BPZSTLSO_WS_ROLE_INFO {
    public BPZSTLSO_WS_ROLE_DATA[] WS_ROLE_DATA = new BPZSTLSO_WS_ROLE_DATA[300];
    public BPZSTLSO_WS_ROLE_INFO() {
        for (int i=0;i<300;i++) WS_ROLE_DATA[i] = new BPZSTLSO_WS_ROLE_DATA();
    }
}
