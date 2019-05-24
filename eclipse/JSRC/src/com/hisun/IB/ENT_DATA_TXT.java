package com.hisun.IB;

public class ENT_DATA_TXT {
    short CNT = 0;
    ENT_EVENT_DATA[] EVENT_DATA = new ENT_EVENT_DATA[75];
    ENT_LSTUPD_INFO LSTUPD_INFO = new ENT_LSTUPD_INFO();
    public ENT_DATA_TXT() {
        for (int i=0;i<75;i++) EVENT_DATA[i] = new ENT_EVENT_DATA();
    }
}
