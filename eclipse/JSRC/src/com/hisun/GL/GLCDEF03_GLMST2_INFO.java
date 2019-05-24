package com.hisun.GL;

public class GLCDEF03_GLMST2_INFO {
    public GLCDEF03_MST2_BASIC MST2_BASIC = new GLCDEF03_MST2_BASIC();
    public GLCDEF03_MST2_REL_ITMS[] MST2_REL_ITMS = new GLCDEF03_MST2_REL_ITMS[60];
    public GLCDEF03_GLMST2_INFO() {
        for (int i=0;i<60;i++) MST2_REL_ITMS[i] = new GLCDEF03_MST2_REL_ITMS();
    }
}
