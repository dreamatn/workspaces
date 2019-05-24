package com.hisun.BP;

public class BPCUGMC_INFO {
    public String CNTR_TYPE = " ";
    public BPCUGMC_CCY_INFO[] CCY_INFO = new BPCUGMC_CCY_INFO[5];
    public char CHG_FLG = ' ';
    public String CI_NO = " ";
    public String AC_NO = " ";
    public String REL_SEQ = " ";
    public int BR_OLD = 0;
    public int BR_NEW = 0;
    public int EFF_DAYS = 0;
    public char CHANGE_FLG = ' ';
    public int OTH_PTR_LEN = 0;
    public Object OTH_PTR_OLD;
    public Object OTH_PTR_NEW;
    public BPCUGMC_GLMS[] GLMS = new BPCUGMC_GLMS[10];
    public BPCUGMC_AMTS[] AMTS = new BPCUGMC_AMTS[76];
    public BPCUGMC_INFO() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCUGMC_CCY_INFO();
        for (int i=0;i<10;i++) GLMS[i] = new BPCUGMC_GLMS();
        for (int i=0;i<76;i++) AMTS[i] = new BPCUGMC_AMTS();
    }
}
