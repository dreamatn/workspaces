package com.hisun.BP;

public class BPCMSQ_QTP_DATA {
    public BPCMSQ_DATA[] DATA = new BPCMSQ_DATA[30];
    public BPCMSQ_QTP_DATA() {
        for (int i=0;i<30;i++) DATA[i] = new BPCMSQ_DATA();
    }
}
