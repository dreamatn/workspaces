package com.hisun.BP;

public class BPOT4710_WS_FMT_DATA {
    String WS_FMT_CAL = " ";
    String WS_FMT_CAL_NE = " ";
    char BPOT4710_FILLER18 = 0X02;
    short WS_FMT_YEAR = 0;
    String WS_FMT_T_CAL = " ";
    String WS_FMT_T_CAL_NE = " ";
    char BPOT4710_FILLER22 = 0X02;
    int WS_FMT_EFF_DT = 0;
    int WS_FMT_EXP_DT = 0;
    short WS_FMT_MON = 0;
    BPOT4710_WS_FMT_DETAIL[] WS_FMT_DETAIL = new BPOT4710_WS_FMT_DETAIL[42];
    public BPOT4710_WS_FMT_DATA() {
        for (int i=0;i<42;i++) WS_FMT_DETAIL[i] = new BPOT4710_WS_FMT_DETAIL();
    }
}
