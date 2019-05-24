package com.hisun.BP;

public class BPOT1334_WS_OUT_PUT_DATA {
    char BPOT1334_FILLER7 = 0X03;
    String WS_TYPE = " ";
    String WS_TYPE_DESC = " ";
    String WS_CODE = " ";
    String WS_CODE_DESC = " ";
    String WS_SEQ_DESC = " ";
    long WS_SEQ_NO = 0;
    short WS_NO_OF_ACCOUNT = 0;
    String WS_AC_OFFICER = " ";
    String WS_REMARK = " ";
    BPOT1334_WS_EVERY_AC_INFO[] WS_EVERY_AC_INFO = new BPOT1334_WS_EVERY_AC_INFO[20];
    public BPOT1334_WS_OUT_PUT_DATA() {
        for (int i=0;i<20;i++) WS_EVERY_AC_INFO[i] = new BPOT1334_WS_EVERY_AC_INFO();
    }
}
