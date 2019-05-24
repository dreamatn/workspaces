package com.hisun.CI;

public class CICFB40 {
    public CICFB40_OUTPUT_TITLE OUTPUT_TITLE = new CICFB40_OUTPUT_TITLE();
    public CICFB40_DATA[] DATA = new CICFB40_DATA[5];
    public CICFB40() {
        for (int i=0;i<5;i++) DATA[i] = new CICFB40_DATA();
    }
}
