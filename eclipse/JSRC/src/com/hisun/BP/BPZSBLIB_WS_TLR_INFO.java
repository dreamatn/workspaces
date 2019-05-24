package com.hisun.BP;

public class BPZSBLIB_WS_TLR_INFO {
    BPZSBLIB_WS_CRT_TLR[] WS_CRT_TLR = new BPZSBLIB_WS_CRT_TLR[5];
    public BPZSBLIB_WS_TLR_INFO() {
        for (int i=0;i<5;i++) WS_CRT_TLR[i] = new BPZSBLIB_WS_CRT_TLR();
    }
}
