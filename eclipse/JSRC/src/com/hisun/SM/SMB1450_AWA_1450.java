package com.hisun.SM;

public class SMB1450_AWA_1450 {
    public String CODE = " ";
    public short CODE_NO = 0;
    public String NAME = " ";
    public short NAME_NO = 0;
    public char TYPE = ' ';
    public short TYPE_NO = 0;
    public String FIRST_Q = " ";
    public short FIRST_Q_NO = 0;
    public String CMPNAME = " ";
    public short CMPNAME_NO = 0;
    public String DAYS = " ";
    public short DAYS_NO = 0;
    public char TLT_FLG = ' ';
    public short TLT_FLG_NO = 0;
    public char DEL_FLG = ' ';
    public short DEL_FLG_NO = 0;
    public char CRBK_FLG = ' ';
    public short CRBK_FLG_NO = 0;
    public String Q_NAME = " ";
    public short Q_NAME_NO = 0;
    public String Q_STS = " ";
    public short Q_STS_NO = 0;
    public char REL_TYPE = ' ';
    public short REL_TYPE_NO = 0;
    public String Q_FMCODE = " ";
    public short Q_FMCODE_NO = 0;
    public String QCODE = " ";
    public short QCODE_NO = 0;
    public char OUT_FLG = ' ';
    public short OUT_FLG_NO = 0;
    public char UPD_FLG = ' ';
    public short UPD_FLG_NO = 0;
    public short STEP_NO = 0;
    public short STEP_NO_NO = 0;
    public String APP_MMO = " ";
    public short APP_MMO_NO = 0;
    public short SEQ = 0;
    public short SEQ_NO = 0;
    public int SUB_BR = 0;
    public short SUB_BR_NO = 0;
    public SMB1450_LVL_DATA[] LVL_DATA = new SMB1450_LVL_DATA[9];
    public SMB1450_DATA[] DATA = new SMB1450_DATA[9];
    public SMB1450_AWA_1450() {
        for (int i=0;i<9;i++) LVL_DATA[i] = new SMB1450_LVL_DATA();
        for (int i=0;i<9;i++) DATA[i] = new SMB1450_DATA();
    }
}
