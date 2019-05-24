package com.hisun.CM;

public class CMCBSPIN {
    public CMCBSPIN_RC RC = new CMCBSPIN_RC();
    public String BV_CODE = " ";
    public CMCBSPIN_INPUT[] INPUT = new CMCBSPIN_INPUT[5];
    public CMCBSPIN() {
        for (int i=0;i<5;i++) INPUT[i] = new CMCBSPIN_INPUT();
    }
}
