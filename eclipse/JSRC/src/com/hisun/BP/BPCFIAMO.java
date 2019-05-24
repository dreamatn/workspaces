package com.hisun.BP;

public class BPCFIAMO {
    public BPCFIAMO_RC RC = new BPCFIAMO_RC();
    public BPCFIAMO_INPUT_DATA INPUT_DATA = new BPCFIAMO_INPUT_DATA();
    public BPCFIAMO_AMO_INFO[] AMO_INFO = new BPCFIAMO_AMO_INFO[20];
    public BPCFIAMO() {
        for (int i=0;i<20;i++) AMO_INFO[i] = new BPCFIAMO_AMO_INFO();
    }
}
