package com.hisun.BP;

public class BPZPVHEC_WS_CUR_GLMST {
    BPZPVHEC_WS_CUR_MST_BASIC WS_CUR_MST_BASIC = new BPZPVHEC_WS_CUR_MST_BASIC();
    BPZPVHEC_WS_CUR_MST_REL_ITMS[] WS_CUR_MST_REL_ITMS = new BPZPVHEC_WS_CUR_MST_REL_ITMS[60];
    public BPZPVHEC_WS_CUR_GLMST() {
        for (int i=0;i<60;i++) WS_CUR_MST_REL_ITMS[i] = new BPZPVHEC_WS_CUR_MST_REL_ITMS();
    }
}