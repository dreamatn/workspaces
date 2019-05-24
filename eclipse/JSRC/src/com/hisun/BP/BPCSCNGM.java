package com.hisun.BP;

public class BPCSCNGM {
    public BPCSCNGM_RC RC = new BPCSCNGM_RC();
    public char FUNC = ' ';
    public BPCSCNGM_KEY KEY = new BPCSCNGM_KEY();
    public String CNTR_TYPE = " ";
    public String PROD_TYPE = " ";
    public int BR = 0;
    public String MOD_NO = " ";
    public BPCSCNGM_DATA[] DATA = new BPCSCNGM_DATA[10];
    public int EC_LMT_DATE = 0;
    public int EC_LMT_TIME = 0;
    public BPCSCNGM() {
        for (int i=0;i<10;i++) DATA[i] = new BPCSCNGM_DATA();
    }
}
