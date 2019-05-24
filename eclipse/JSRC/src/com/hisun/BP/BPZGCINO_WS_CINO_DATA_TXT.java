package com.hisun.BP;

public class BPZGCINO_WS_CINO_DATA_TXT {
    short WS_CINO_CTL_LENGTH = 0;
    char WS_CINO_IN_USE_FLG = ' ';
    BPZGCINO_WS_CINO_CLT[] WS_CINO_CLT = new BPZGCINO_WS_CINO_CLT[8];
    short WS_CINO_SEQ_LENGTH = 0;
    char WS_CINO_FUNC_FLG = ' ';
    public BPZGCINO_WS_CINO_DATA_TXT() {
        for (int i=0;i<8;i++) WS_CINO_CLT[i] = new BPZGCINO_WS_CINO_CLT();
    }
}
