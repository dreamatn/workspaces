package com.hisun.CI;

public class CICFB10 {
    public CICFB10_OUTPUT_TITLE OUTPUT_TITLE = new CICFB10_OUTPUT_TITLE();
    public CICFB10_DATA[] DATA = new CICFB10_DATA[10];
    public CICFB10() {
        for (int i=0;i<10;i++) DATA[i] = new CICFB10_DATA();
    }
}
