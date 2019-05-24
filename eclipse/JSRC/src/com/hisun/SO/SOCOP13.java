package com.hisun.SO;

public class SOCOP13 {
    public short BANK_NO = 0;
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
    public long NEXT_JRN_NO2 = 0;
    public char JRN_IN_USE = ' ';
    public char MST_IN_USE = ' ';
    public char PARM_IN_USE = ' ';
    public SOCOP13_AC_DATE_AREA[] AC_DATE_AREA = new SOCOP13_AC_DATE_AREA[10];
    public int BAT_END_DATE = 0;
    public short TIME_DIF = 0;
    public char FLAG = ' ';
    public char AUDIT_IND = ' ';
    public String NC_POOL = " ";
    public String COMMIT_INTVAL = " ";
    public String COMMIT_INTVAL2 = " ";
    public String COMMIT_INTVAL3 = " ";
    public String COMMIT_INTVAL4 = " ";
    public String CARD_START_TIME = " ";
    public String CARD_END_TIME = " ";
    public SOCOP13_AP_AREA[] AP_AREA = new SOCOP13_AP_AREA[64];
    public SOCOP13_FLOW_AREA[] FLOW_AREA = new SOCOP13_FLOW_AREA[80];
    public SOCOP13() {
        for (int i=0;i<10;i++) AC_DATE_AREA[i] = new SOCOP13_AC_DATE_AREA();
        for (int i=0;i<64;i++) AP_AREA[i] = new SOCOP13_AP_AREA();
        for (int i=0;i<80;i++) FLOW_AREA[i] = new SOCOP13_FLOW_AREA();
    }
}
