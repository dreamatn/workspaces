package com.hisun.GL;

public class GLCDEF01_GLMST2_INFO {
    public GLCDEF01_MST2_BASIC MST2_BASIC = new GLCDEF01_MST2_BASIC();
    public GLCDEF01_MST2_REL_ITMS[] MST2_REL_ITMS = new GLCDEF01_MST2_REL_ITMS[60];
    public GLCDEF01_GLMST2_INFO() {
        for (int i=0;i<60;i++) MST2_REL_ITMS[i] = new GLCDEF01_MST2_REL_ITMS();
    }
}
