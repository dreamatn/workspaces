package com.hisun.BP;

public class BPCDEF04_GLMST1_INFO {
    public BPCDEF04_MST1_BASIC MST1_BASIC = new BPCDEF04_MST1_BASIC();
    public BPCDEF04_MST1_REL_ITMS[] MST1_REL_ITMS = new BPCDEF04_MST1_REL_ITMS[60];
    public BPCDEF04_GLMST1_INFO() {
        for (int i=0;i<60;i++) MST1_REL_ITMS[i] = new BPCDEF04_MST1_REL_ITMS();
    }
}
