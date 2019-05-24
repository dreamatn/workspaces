package com.hisun.BP;

public class BPRENTY_DATA_TXT {
    public short CNT = 0;
    public BPRENTY_EVENT_DATA[] EVENT_DATA = new BPRENTY_EVENT_DATA[75];
    public BPRENTY_LSTUPD_INFO LSTUPD_INFO = new BPRENTY_LSTUPD_INFO();
    public BPRENTY_DATA_TXT() {
        for (int i=0;i<75;i++) EVENT_DATA[i] = new BPRENTY_EVENT_DATA();
    }
}
