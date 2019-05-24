package com.hisun.BP;

public class BPCUCNGM {
    public BPCUCNGM_RC RC = new BPCUCNGM_RC();
    public char FUNC = ' ';
    public BPCUCNGM_KEY KEY = new BPCUCNGM_KEY();
    public String PROD_TYPE = " ";
    public int BR = 0;
    public String MOD_NO = " ";
    public BPCUCNGM_DATA[] DATA = new BPCUCNGM_DATA[10];
    public int EC_LMT_DATE = 0;
    public int EC_LMT_TIME = 0;
    public BPCUCNGM() {
        for (int i=0;i<10;i++) DATA[i] = new BPCUCNGM_DATA();
    }
}
