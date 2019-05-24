package com.hisun.BP;

public class BPZSPBL_WS_OUTPUT_DETAIL {
    short WS_OPBL_CNT = 0;
    String WS_OPBL_ITM_PNT = " ";
    String WS_OPBL_ITM_NO = " ";
    int WS_OPBL_ITM_SEQ = 0;
    BPZSPBL_WS_OPBL_AMT_PNT[] WS_OPBL_AMT_PNT = new BPZSPBL_WS_OPBL_AMT_PNT[20];
    String WS_OPBL_REMARK = " ";
    char BPZSPBL_FILLER45 = 0X02;
    public BPZSPBL_WS_OUTPUT_DETAIL() {
        for (int i=0;i<20;i++) WS_OPBL_AMT_PNT[i] = new BPZSPBL_WS_OPBL_AMT_PNT();
    }
}
