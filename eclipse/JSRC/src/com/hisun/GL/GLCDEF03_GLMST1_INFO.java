package com.hisun.GL;

public class GLCDEF03_GLMST1_INFO {
    public GLCDEF03_MST1_BASIC MST1_BASIC = new GLCDEF03_MST1_BASIC();
    public GLCDEF03_MST1_REL_ITMS[] MST1_REL_ITMS = new GLCDEF03_MST1_REL_ITMS[60];
    public GLCDEF03_GLMST1_INFO() {
        for (int i=0;i<60;i++) MST1_REL_ITMS[i] = new GLCDEF03_MST1_REL_ITMS();
    }
}
