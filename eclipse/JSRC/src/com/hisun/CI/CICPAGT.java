package com.hisun.CI;

public class CICPAGT {
    public char CLO_CTL = ' ';
    public String SIGN_CHNL = " ";
    public String USE_CHNL = " ";
    public char AGT_CLS = ' ';
    public char AGT_LVL = ' ';
    public String FRM_APP = " ";
    public char CHANGE_CARD = ' ';
    public CICPAGT_MUTEX_DATA[] MUTEX_DATA = new CICPAGT_MUTEX_DATA[12];
    public String REMARK = " ";
    public CICPAGT() {
        for (int i=0;i<12;i++) MUTEX_DATA[i] = new CICPAGT_MUTEX_DATA();
    }
}
