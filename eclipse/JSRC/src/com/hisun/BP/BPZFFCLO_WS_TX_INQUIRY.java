package com.hisun.BP;

public class BPZFFCLO_WS_TX_INQUIRY {
    String WSI_CMMT_NO = " ";
    String WSI_AC = " ";
    String WSI_AC_NAME = " ";
    char BPZFFCLO_FILLER7 = 0X02;
    int WSI_PROC_DT = 0;
    BPZFFCLO_WSI_CLT_LOOP[] WSI_CLT_LOOP = new BPZFFCLO_WSI_CLT_LOOP[5];
    public BPZFFCLO_WS_TX_INQUIRY() {
        for (int i=0;i<5;i++) WSI_CLT_LOOP[i] = new BPZFFCLO_WSI_CLT_LOOP();
    }
}
