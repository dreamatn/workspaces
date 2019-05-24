package com.hisun.BP;

public class BPZPVHEC_WS_EVENT_VCH {
    String WS_EVN_MOD_NO = " ";
    String WS_EVN_EVENT_CD = " ";
    short WS_EVN_CNT = 0;
    BPZPVHEC_WS_EVN_VCH_DETAIL[] WS_EVN_VCH_DETAIL = new BPZPVHEC_WS_EVN_VCH_DETAIL[1000];
    public BPZPVHEC_WS_EVENT_VCH() {
        for (int i=0;i<1000;i++) WS_EVN_VCH_DETAIL[i] = new BPZPVHEC_WS_EVN_VCH_DETAIL();
    }
}
