package com.hisun.LN;

public class LNZSPDQ_WS_RCV_DATAS {
    int CNT = 0;
    LNZSPDQ_WS_RCV_DATA[] WS_RCV_DATA = new LNZSPDQ_WS_RCV_DATA[1500];
    public LNZSPDQ_WS_RCV_DATAS() {
        for (int i=0;i<1500;i++) WS_RCV_DATA[i] = new LNZSPDQ_WS_RCV_DATA();
    }
}
