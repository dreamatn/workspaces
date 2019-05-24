package com.hisun.BP;

public class BPZSBVRG_WS_BVRG_DETAIL {
    char WS_FUNC = ' ';
    String WS_BV_CODE = " ";
    BPZSBVRG_SDNCS_BR_INFO[] SDNCS_BR_INFO = new BPZSBVRG_SDNCS_BR_INFO[30];
    String WS_BV_CNM = " ";
    String WS_BV_ENM = " ";
    int WS_UPD_DATE = 0;
    String WS_UPD_TLR = " ";
    int WS_CRT_DATE = 0;
    String WS_CRT_TLR = " ";
    public BPZSBVRG_WS_BVRG_DETAIL() {
        for (int i=0;i<30;i++) SDNCS_BR_INFO[i] = new BPZSBVRG_SDNCS_BR_INFO();
    }
}
