package com.hisun.BP;

public class BPZFPLCK_WS_ERROR_INFO {
    BPZFPLCK_WS_BOX_INFO[] WS_BOX_INFO = new BPZFPLCK_WS_BOX_INFO[10];
    public BPZFPLCK_WS_ERROR_INFO() {
        for (int i=0;i<10;i++) WS_BOX_INFO[i] = new BPZFPLCK_WS_BOX_INFO();
    }
}
