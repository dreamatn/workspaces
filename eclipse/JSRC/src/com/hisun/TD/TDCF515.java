package com.hisun.TD;

public class TDCF515 {
    public TDCF515_PAGE_INF PAGE_INF = new TDCF515_PAGE_INF();
    public TDCF515_DATA[] DATA = new TDCF515_DATA[8];
    public TDCF515() {
        for (int i=0;i<8;i++) DATA[i] = new TDCF515_DATA();
    }
}
