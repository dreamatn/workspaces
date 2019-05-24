package com.hisun.BP;

public class BPRGFHIS_INFO {
    public String CNTR_TYP = " ";
    public String CCY = " ";
    public String CI_NO = " ";
    public String AC_NO = " ";
    public String REF = " ";
    public int BR = 0;
    public String OTH_OLD = " ";
    public String OTH_NEW = " ";
    public BPRGFHIS_GLMS[] GLMS = new BPRGFHIS_GLMS[10];
    public BPRGFHIS_AMTS[] AMTS = new BPRGFHIS_AMTS[20];
    public String MOD_NO = " ";
    public BPRGFHIS_INFO() {
        for (int i=0;i<10;i++) GLMS[i] = new BPRGFHIS_GLMS();
        for (int i=0;i<20;i++) AMTS[i] = new BPRGFHIS_AMTS();
    }
}
