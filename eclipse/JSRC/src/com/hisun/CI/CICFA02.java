package com.hisun.CI;

public class CICFA02 {
    public CICFA02_OUTPUT_TITLE OUTPUT_TITLE = new CICFA02_OUTPUT_TITLE();
    public CICFA02_DATA[] DATA = new CICFA02_DATA[10];
    public CICFA02() {
        for (int i=0;i<10;i++) DATA[i] = new CICFA02_DATA();
    }
}
