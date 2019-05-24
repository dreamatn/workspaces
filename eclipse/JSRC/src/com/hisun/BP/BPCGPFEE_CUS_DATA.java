package com.hisun.BP;

public class BPCGPFEE_CUS_DATA {
    public short CUS_CNT = 0;
    public BPCGPFEE_CUS_INFO[] CUS_INFO = new BPCGPFEE_CUS_INFO[5];
    public BPCGPFEE_CUS_DATA() {
        for (int i=0;i<5;i++) CUS_INFO[i] = new BPCGPFEE_CUS_INFO();
    }
}
