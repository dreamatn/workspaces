package com.hisun.DD;

public class DDZSCLAC_WS_CHQ_INFO {
    DDZSCLAC_WS_AC_CCHQ[] WS_AC_CCHQ = new DDZSCLAC_WS_AC_CCHQ[10];
    DDZSCLAC_WS_AC_TCHQ[] WS_AC_TCHQ = new DDZSCLAC_WS_AC_TCHQ[10];
    DDZSCLAC_WS_AC_DCHQ[] WS_AC_DCHQ = new DDZSCLAC_WS_AC_DCHQ[10];
    public DDZSCLAC_WS_CHQ_INFO() {
        for (int i=0;i<10;i++) WS_AC_CCHQ[i] = new DDZSCLAC_WS_AC_CCHQ();
        for (int i=0;i<10;i++) WS_AC_TCHQ[i] = new DDZSCLAC_WS_AC_TCHQ();
        for (int i=0;i<10;i++) WS_AC_DCHQ[i] = new DDZSCLAC_WS_AC_DCHQ();
    }
}
