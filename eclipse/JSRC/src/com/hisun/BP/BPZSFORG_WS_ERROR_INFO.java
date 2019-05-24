package com.hisun.BP;

public class BPZSFORG_WS_ERROR_INFO {
    BPZSFORG_WS_AC_INFO[] WS_AC_INFO = new BPZSFORG_WS_AC_INFO[30];
    String WS_MORE_INFO = " ";
    public BPZSFORG_WS_ERROR_INFO() {
        for (int i=0;i<30;i++) WS_AC_INFO[i] = new BPZSFORG_WS_AC_INFO();
    }
}
