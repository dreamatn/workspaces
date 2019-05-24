package com.hisun.GL;

public class GLCDEF01_GLMST1_INFO {
    public GLCDEF01_MST1_BASIC MST1_BASIC = new GLCDEF01_MST1_BASIC();
    public GLCDEF01_MST1_REL_ITMS[] MST1_REL_ITMS = new GLCDEF01_MST1_REL_ITMS[60];
    public GLCDEF01_GLMST1_INFO() {
        for (int i=0;i<60;i++) MST1_REL_ITMS[i] = new GLCDEF01_MST1_REL_ITMS();
    }
}
