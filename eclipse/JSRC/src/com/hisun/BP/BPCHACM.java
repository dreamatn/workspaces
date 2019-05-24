package com.hisun.BP;

public class BPCHACM {
    public String CNTR_TYPE = " ";
    public String PROD_TYPE = " ";
    public int BR = 0;
    public String MOD_NO = " ";
    public String MOD_NAME = " ";
    public char MOD_TYP = ' ';
    public BPCHACM_EVENT[] EVENT = new BPCHACM_EVENT[60];
    public BPCHACM() {
        for (int i=0;i<60;i++) EVENT[i] = new BPCHACM_EVENT();
    }
}
