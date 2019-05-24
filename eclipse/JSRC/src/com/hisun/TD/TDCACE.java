package com.hisun.TD;

public class TDCACE {
    public TDCACE_PAGE_INF PAGE_INF = new TDCACE_PAGE_INF();
    public TDCACE_DATA[] DATA = new TDCACE_DATA[6];
    public char FMT_FLG = ' ';
    public char CALL_STS = ' ';
    public TDCACE() {
        for (int i=0;i<6;i++) DATA[i] = new TDCACE_DATA();
    }
}
