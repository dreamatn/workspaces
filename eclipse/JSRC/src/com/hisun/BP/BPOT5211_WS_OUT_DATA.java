package com.hisun.BP;

public class BPOT5211_WS_OUT_DATA {
    int WS_X_BR = 0;
    BPOT5211_WS_X_RT_INFO[] WS_X_RT_INFO = new BPOT5211_WS_X_RT_INFO[30];
    public BPOT5211_WS_OUT_DATA() {
        for (int i=0;i<30;i++) WS_X_RT_INFO[i] = new BPOT5211_WS_X_RT_INFO();
    }
}
