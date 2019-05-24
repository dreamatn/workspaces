package com.hisun.BP;

public class BPOT1338_WS_OUTPUT_DATA {
    String WS_ACNO_TYPE = " ";
    String WS_ACNO_TYPE_DESC = " ";
    String WS_ACNO_CODE = " ";
    String WS_ACNO_CODE_DESC = " ";
    String WS_ACNO_SEQ_DESC = " ";
    String WS_ACNO_AC_OFC = " ";
    String WS_ACNO_REMARK = " ";
    BPOT1338_WS_PRE_HOLD_DAT[] WS_PRE_HOLD_DAT = new BPOT1338_WS_PRE_HOLD_DAT[20];
    public BPOT1338_WS_OUTPUT_DATA() {
        for (int i=0;i<20;i++) WS_PRE_HOLD_DAT[i] = new BPOT1338_WS_PRE_HOLD_DAT();
    }
}
