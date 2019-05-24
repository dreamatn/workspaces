package com.hisun.LN;

public class LNCIGVCH_DATA {
    public String CNTR_TYPE = " ";
    public String PROD_CODE_OLD = " ";
    public String PROD_CODE_NEW = " ";
    public String CTA_NO = " ";
    public int SUB_CTA_NO = 0;
    public String EVENT_CODE = " ";
    public int BR_OLD = 0;
    public int BR_NEW = 0;
    public LNCIGVCH_CCY_INFO[] CCY_INFO = new LNCIGVCH_CCY_INFO[5];
    public int VALUE_DATE = 0;
    public String STATUS = " ";
    public double BAL_NORMAL = 0;
    public double BAL_NORDUE = 0;
    public double BAL_OVERDUE_MANUAL = 0;
    public double BAL_OVERDUE = 0;
    public LNCIGVCH_AMT_INFO[] AMT_INFO = new LNCIGVCH_AMT_INFO[60];
    public String CI_NO = " ";
    public String REF_NO = " ";
    public String PORT = " ";
    public String DESC = " ";
    public int EFF_DAYS = 0;
    public char CLO_FILE_FLG = ' ';
    public String FILLER = " ";
    public LNCIGVCH_DATA() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new LNCIGVCH_CCY_INFO();
        for (int i=0;i<60;i++) AMT_INFO[i] = new LNCIGVCH_AMT_INFO();
    }
}
