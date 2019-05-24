package com.hisun.CI;

public class CICOACLS {
    public CICOACLS_OUTPUT_TITLE OUTPUT_TITLE = new CICOACLS_OUTPUT_TITLE();
    public CICOACLS_DATA[] DATA = new CICOACLS_DATA[51];
    public CICOACLS() {
        for (int i=0;i<51;i++) DATA[i] = new CICOACLS_DATA();
    }
}
