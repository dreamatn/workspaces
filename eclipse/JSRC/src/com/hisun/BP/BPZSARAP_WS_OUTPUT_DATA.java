package com.hisun.BP;

public class BPZSARAP_WS_OUTPUT_DATA {
    int WS_APP_NO = 0;
    char WS_APP_STS = ' ';
    int WS_APP_BR = 0;
    String WS_APP_TLR = " ";
    int WS_APP_DT = 0;
    BPZSARAP_WS_CCY_INFO[] WS_CCY_INFO = new BPZSARAP_WS_CCY_INFO[5];
    int WS_UP_BR = 0;
    int WS_MOVE_DT = 0;
    String WS_MOVE_TLR = " ";
    int WS_CONF_DT = 0;
    String WS_CONF_TLR = " ";
    int WS_CONF_NO = 0;
    public BPZSARAP_WS_OUTPUT_DATA() {
        for (int i=0;i<5;i++) WS_CCY_INFO[i] = new BPZSARAP_WS_CCY_INFO();
    }
}
