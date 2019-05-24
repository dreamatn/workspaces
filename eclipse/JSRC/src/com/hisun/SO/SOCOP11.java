package com.hisun.SO;

public class SOCOP11 {
    public char ACTION = ' ';
    public SOCOP11_KEY KEY = new SOCOP11_KEY();
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
    public SOCOP11_AC_DATE_AREA[] AC_DATE_AREA = new SOCOP11_AC_DATE_AREA[2];
    public int BAT_END_DATE = 0;
    public short TIME_DIF = 0;
    public char FILLER26 = 0X01;
    public char AUDIT_IND = ' ';
    public String NC_POOL = " ";
    public String COMMIT_INTVAL = " ";
    public String COMMIT_INTVAL2 = " ";
    public String COMMIT_INTVAL3 = " ";
    public String COMMIT_INTVAL4 = " ";
    public String CARD_START_TIME = " ";
    public String CARD_END_TIME = " ";
    public SOCOP11_AP_AREA[] AP_AREA = new SOCOP11_AP_AREA[64];
    public SOCOP11_FLOW_AREA[] FLOW_AREA = new SOCOP11_FLOW_AREA[80];
    public SOCOP11() {
        for (int i=0;i<2;i++) AC_DATE_AREA[i] = new SOCOP11_AC_DATE_AREA();
        for (int i=0;i<64;i++) AP_AREA[i] = new SOCOP11_AP_AREA();
        for (int i=0;i<80;i++) FLOW_AREA[i] = new SOCOP11_FLOW_AREA();
    }
}
