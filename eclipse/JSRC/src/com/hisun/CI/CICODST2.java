package com.hisun.CI;

public class CICODST2 {
    public CICODST2_OUTPUT_TITLE OUTPUT_TITLE = new CICODST2_OUTPUT_TITLE();
    public CICODST2_DATA[] DATA = new CICODST2_DATA[10];
    public CICODST2() {
        for (int i=0;i<10;i++) DATA[i] = new CICODST2_DATA();
    }
}
