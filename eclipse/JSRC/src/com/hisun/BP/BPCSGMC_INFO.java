package com.hisun.BP;

public class BPCSGMC_INFO {
    public String CNTR_TYP = " ";
    public String CCY = " ";
    public String CI_NO = " ";
    public String AC_NO = " ";
    public String REF = " ";
    public int BR = 0;
    public String OTH_OLD = " ";
    public String OTH_NEW = " ";
    public BPCSGMC_GLMS[] GLMS = new BPCSGMC_GLMS[10];
    public BPCSGMC_AMTS[] AMTS = new BPCSGMC_AMTS[20];
    public String MOD_NO = " ";
    public BPCSGMC_INFO() {
        for (int i=0;i<10;i++) GLMS[i] = new BPCSGMC_GLMS();
        for (int i=0;i<20;i++) AMTS[i] = new BPCSGMC_AMTS();
    }
}