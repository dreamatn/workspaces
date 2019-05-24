package com.hisun.GL;

public class GLCDEF02_CUR_GLMST {
    public GLCDEF02_CUR_MST_BASIC CUR_MST_BASIC = new GLCDEF02_CUR_MST_BASIC();
    public GLCDEF02_CUR_MST_REL_ITMS[] CUR_MST_REL_ITMS = new GLCDEF02_CUR_MST_REL_ITMS[60];
    public GLCDEF02_CUR_GLMST() {
        for (int i=0;i<60;i++) CUR_MST_REL_ITMS[i] = new GLCDEF02_CUR_MST_REL_ITMS();
    }
}
