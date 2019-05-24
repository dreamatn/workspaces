package com.hisun.GL;

public class GLCDEF02_GLMST2_INFO {
    public GLCDEF02_MST2_BASIC MST2_BASIC = new GLCDEF02_MST2_BASIC();
    public GLCDEF02_MST2_REL_ITMS[] MST2_REL_ITMS = new GLCDEF02_MST2_REL_ITMS[60];
    public GLCDEF02_GLMST2_INFO() {
        for (int i=0;i<60;i++) MST2_REL_ITMS[i] = new GLCDEF02_MST2_REL_ITMS();
    }
}
