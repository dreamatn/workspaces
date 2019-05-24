package com.hisun.BP;

public class BPOT5171_WS_OUTPUT_DATA {
    int WS_L_TOTAL_ROW = 0;
    int WS_L_CURR_MAX_ROW = 0;
    char WS_L_END_FLG = ' ';
    BPOT5171_WS_L_DATA[] WS_L_DATA = new BPOT5171_WS_L_DATA[50];
    public BPOT5171_WS_OUTPUT_DATA() {
        for (int i=0;i<50;i++) WS_L_DATA[i] = new BPOT5171_WS_L_DATA();
    }
}
