package com.hisun.BP;

public class BPCSGBAL_OUTPUT {
    public short REC_NUM = 0;
    public BPCSGBAL_REC_BODY[] REC_BODY = new BPCSGBAL_REC_BODY[30];
    public BPCSGBAL_OUTPUT() {
        for (int i=0;i<30;i++) REC_BODY[i] = new BPCSGBAL_REC_BODY();
    }
}
