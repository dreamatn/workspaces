package com.hisun.CM;

public class CMZS0018_WS_RATE_DATA {
    CMZS0018_WS_TAX_ARRY[] WS_TAX_ARRY = new CMZS0018_WS_TAX_ARRY[10];
    public CMZS0018_WS_RATE_DATA() {
        for (int i=0;i<10;i++) WS_TAX_ARRY[i] = new CMZS0018_WS_TAX_ARRY();
    }
}
