package com.hisun.BP;

public class BPZSTLSO_WS_TATH_INFO {
    public BPZSTLSO_WS_TATH_DATA[] WS_TATH_DATA = new BPZSTLSO_WS_TATH_DATA[300];
    public BPZSTLSO_WS_TATH_INFO() {
        for (int i=0;i<300;i++) WS_TATH_DATA[i] = new BPZSTLSO_WS_TATH_DATA();
    }
}
