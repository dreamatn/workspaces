package com.hisun.TD;

public class TDCBPCU {
    public String ACTI_NO = " ";
    public TDCBPCU_BR_NO[] BR_NO = new TDCBPCU_BR_NO[500];
    public TDCBPCU_CFLG[] CFLG = new TDCBPCU_CFLG[500];
    public TDCBPCU() {
        for (int i=0;i<500;i++) BR_NO[i] = new TDCBPCU_BR_NO();
        for (int i=0;i<500;i++) CFLG[i] = new TDCBPCU_CFLG();
    }
}
