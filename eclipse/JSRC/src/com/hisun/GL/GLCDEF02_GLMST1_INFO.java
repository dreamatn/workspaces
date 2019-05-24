package com.hisun.GL;

public class GLCDEF02_GLMST1_INFO {
    public GLCDEF02_MST1_BASIC MST1_BASIC = new GLCDEF02_MST1_BASIC();
    public GLCDEF02_MST1_REL_ITMS[] MST1_REL_ITMS = new GLCDEF02_MST1_REL_ITMS[60];
    public GLCDEF02_GLMST1_INFO() {
        for (int i=0;i<60;i++) MST1_REL_ITMS[i] = new GLCDEF02_MST1_REL_ITMS();
    }
}
