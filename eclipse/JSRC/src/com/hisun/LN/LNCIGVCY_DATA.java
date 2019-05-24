package com.hisun.LN;

public class LNCIGVCY_DATA {
    public String CNTR_TYPE = " ";
    public String PROD_CODE_OLD = " ";
    public String PROD_CODE_NEW = " ";
    public String CTA_NO = " ";
    public int SUB_CTA_NO = 0;
    public String EVENT_CODE = " ";
    public int BR_OLD = 0;
    public int BR_NEW = 0;
    public LNCIGVCY_CCY_INFO[] CCY_INFO = new LNCIGVCY_CCY_INFO[5];
    public int VALUE_DATE = 0;
    public String STATUS = " ";
    public double BAL_NORMAL = 0;
    public double BAL_NORDUE = 0;
    public double BAL_OVERDUE_MANUAL = 0;
    public double BAL_OVERDUE = 0;
    public LNCIGVCY_AMT_INFO[] AMT_INFO = new LNCIGVCY_AMT_INFO[80];
    public String CI_NO = " ";
    public String REF_NO = " ";
    public String PORT = " ";
    public String DESC = " ";
    public int EFF_DAYS = 0;
    public char CLO_FILE_FLG = ' ';
    public String FILLER = " ";
    public char ACCEPT_FLG = ' ';
    public String DEP_AC = " ";
    public LNCIGVCY_ACCEPT_DATA ACCEPT_DATA = new LNCIGVCY_ACCEPT_DATA();
    public LNCIGVCY_DATA() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new LNCIGVCY_CCY_INFO();
        for (int i=0;i<80;i++) AMT_INFO[i] = new LNCIGVCY_AMT_INFO();
    }
}
