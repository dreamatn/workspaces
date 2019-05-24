package com.hisun.BP;

public class BPCOFCLT {
    public BPCOFCLT_OUTPUT_TITLE OUTPUT_TITLE = new BPCOFCLT_OUTPUT_TITLE();
    public BPCOFCLT_TOTAL_DATA TOTAL_DATA = new BPCOFCLT_TOTAL_DATA();
    public BPCOFCLT_DATA[] DATA = new BPCOFCLT_DATA[15];
    public BPCOFCLT() {
        for (int i=0;i<15;i++) DATA[i] = new BPCOFCLT_DATA();
    }
}
