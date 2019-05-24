package com.hisun.SO;

public class SOCICWA {
    public char ACTION = ' ';
    public SOCICWA_KEY KEY = new SOCICWA_KEY();
    public String VERSION_NO = " ";
    public String SYS_ID = " ";
    public char SYS_DEST = ' ';
    public char SYS_STUS = ' ';
    public char SERV_LVL = ' ';
    public char BUSS_MODE = ' ';
    public char LOAD_PARM_IND = ' ';
    public long WARN_JRN_NO = 0;
    public short ACT_TR_LMT = 0;
    public short ACT_LTR_LMT = 0;
    public short BTP_JOB_LMT = 0;
    public long NEXT_JRN_NO1 = 0;
    public char JRN_IN_USE = ' ';
    public char MST_IN_USE = ' ';
    public char PARM_IN_USE = ' ';
    public long NEXT_JRN_NO2 = 0;
    public SOCICWA_AC_DATE_AREA[] AC_DATE_AREA = new SOCICWA_AC_DATE_AREA[2];
    public int BAT_END_DATE = 0;
    public short TIME_DIF = 0;
    public char AUDIT_IND = ' ';
    public String NC_POOL = " ";
    public String COMMIT_INTVAL = " ";
    public String COMMIT_INTVAL2 = " ";
    public String COMMIT_INTVAL3 = " ";
    public String COMMIT_INTVAL4 = " ";
    public String CARD_START_TIME = " ";
    public String CARD_END_TIME = " ";
    public SOCICWA_TABLE_AP TABLE_AP = new SOCICWA_TABLE_AP();
    public String AP_AREA = " ";
    public SOCICWA_TABLE_FLOW TABLE_FLOW = new SOCICWA_TABLE_FLOW();
    public String FLOW_AREA = " ";
    public SOCICWA() {
        for (int i=0;i<2;i++) AC_DATE_AREA[i] = new SOCICWA_AC_DATE_AREA();
    }
}
