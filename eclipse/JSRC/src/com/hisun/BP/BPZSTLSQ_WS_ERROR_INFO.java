package com.hisun.BP;

public class BPZSTLSQ_WS_ERROR_INFO {
    BPZSTLSQ_WS_AC_INFO[] WS_AC_INFO = new BPZSTLSQ_WS_AC_INFO[30];
    String WS_MORE_INFO = " ";
    public BPZSTLSQ_WS_ERROR_INFO() {
        for (int i=0;i<30;i++) WS_AC_INFO[i] = new BPZSTLSQ_WS_AC_INFO();
    }
}
