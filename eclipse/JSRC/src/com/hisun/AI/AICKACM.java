package com.hisun.AI;

public class AICKACM {
    public String CNTR_TYPE = " ";
    public String PROD_TYPE = " ";
    public int BR = 0;
    public String KEY_ERR_MSG = " ";
    public AICKACM_ENENT_MSG[] ENENT_MSG = new AICKACM_ENENT_MSG[60];
    public AICKACM() {
        for (int i=0;i<60;i++) ENENT_MSG[i] = new AICKACM_ENENT_MSG();
    }
}
