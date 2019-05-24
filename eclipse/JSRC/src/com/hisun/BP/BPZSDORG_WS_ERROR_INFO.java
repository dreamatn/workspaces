package com.hisun.BP;

public class BPZSDORG_WS_ERROR_INFO {
    BPZSDORG_WS_AC_INFO[] WS_AC_INFO = new BPZSDORG_WS_AC_INFO[30];
    String WS_MORE_INFO = " ";
    public BPZSDORG_WS_ERROR_INFO() {
        for (int i=0;i<30;i++) WS_AC_INFO[i] = new BPZSDORG_WS_AC_INFO();
    }
}
