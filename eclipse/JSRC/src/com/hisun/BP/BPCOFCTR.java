package com.hisun.BP;

public class BPCOFCTR {
    public String FEE_CODE = " ";
    public String FEE_DESC = " ";
    public char FILLER3 = 0X02;
    public String CI_NO = " ";
    public char REL_SRC = ' ';
    public String REL_CTRT = " ";
    public char AC_TYP = ' ';
    public String CHG_MEDIUM = " ";
    public String CHG_CCY = " ";
    public char CCY_TYPE = ' ';
    public double CHG_AMT = 0;
    public String FILLER12 = " ";
    public int CTRT_CNT_TOT = 0;
    public BPCOFCTR_CTRT_INFO[] CTRT_INFO = new BPCOFCTR_CTRT_INFO[30];
    public BPCOFCTR() {
        for (int i=0;i<30;i++) CTRT_INFO[i] = new BPCOFCTR_CTRT_INFO();
    }
}
