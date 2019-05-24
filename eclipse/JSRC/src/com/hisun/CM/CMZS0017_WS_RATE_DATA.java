package com.hisun.CM;

public class CMZS0017_WS_RATE_DATA {
    CMZS0017_WS_TAX_ARRY[] WS_TAX_ARRY = new CMZS0017_WS_TAX_ARRY[10];
    public CMZS0017_WS_RATE_DATA() {
        for (int i=0;i<10;i++) WS_TAX_ARRY[i] = new CMZS0017_WS_TAX_ARRY();
    }
}
