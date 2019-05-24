package com.hisun.BP;

public class BPCDEF04_CUR_GLMST {
    public BPCDEF04_CUR_MST_BASIC CUR_MST_BASIC = new BPCDEF04_CUR_MST_BASIC();
    public BPCDEF04_CUR_MST_REL_ITMS[] CUR_MST_REL_ITMS = new BPCDEF04_CUR_MST_REL_ITMS[60];
    public BPCDEF04_CUR_GLMST() {
        for (int i=0;i<60;i++) CUR_MST_REL_ITMS[i] = new BPCDEF04_CUR_MST_REL_ITMS();
    }
}
