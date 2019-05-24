package com.hisun.BP;

public class BPOT4700_WS_FMT_DATA {
    String WS_FMT_CAL = " ";
    String WS_FMT_CAL_NE = " ";
    char BPOT4700_FILLER18 = 0X02;
    short WS_FMT_YEAR = 0;
    int WS_FMT_EFF_DT = 0;
    int WS_FMT_EXP_DT = 0;
    short WS_FMT_MON = 0;
    BPOT4700_WS_FMT_DETAIL[] WS_FMT_DETAIL = new BPOT4700_WS_FMT_DETAIL[42];
    public BPOT4700_WS_FMT_DATA() {
        for (int i=0;i<42;i++) WS_FMT_DETAIL[i] = new BPOT4700_WS_FMT_DETAIL();
    }
}
