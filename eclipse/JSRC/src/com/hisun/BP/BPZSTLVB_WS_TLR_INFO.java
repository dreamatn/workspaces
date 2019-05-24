package com.hisun.BP;

public class BPZSTLVB_WS_TLR_INFO {
    BPZSTLVB_WS_CRT_TLR[] WS_CRT_TLR = new BPZSTLVB_WS_CRT_TLR[5];
    public BPZSTLVB_WS_TLR_INFO() {
        for (int i=0;i<5;i++) WS_CRT_TLR[i] = new BPZSTLVB_WS_CRT_TLR();
    }
}
