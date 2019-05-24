package com.hisun.CI;

public class CICI9032 {
    public String CI_NO = " ";
    public String ID_TYPE = " ";
    public String ID_NO = " ";
    public int EXP_DT = 0;
    public String CI_NM = " ";
    public String CI_ENM = " ";
    public char CI_TYP = ' ';
    public char CI_ATTR = ' ';
    public String STSW = " ";
    public int OPEN_DT = 0;
    public int OWNER_BK = 0;
    public int CLOSE_DT = 0;
    public String OPN_CHNL = " ";
    public String SUB_TYP = " ";
    public String ORG_TYPE = " ";
    public char FTA_FLG = ' ';
    public String SVR_LVL = " ";
    public String TAX_BANK = " ";
    public String TAX_AC = " ";
    public char TAX_TYPE = ' ';
    public String TAX_DSNO = " ";
    public char RESIDENT = ' ';
    public String INT_TYPE = " ";
    public String INDUS1 = " ";
    public char SID_FLG = ' ';
    public String PB_AP_NO = " ";
    public String OIC_NO = " ";
    public String RESP_CD = " ";
    public String SUB_DP = " ";
    public char INFO_IND = ' ';
    public char AG_FLG = ' ';
    public String AG_TP = " ";
    public String AG_ID_TP = " ";
    public String AG_ID_NO = " ";
    public String AG_NAME = " ";
    public String AG_TEL = " ";
    public char AG_SEX = ' ';
    public int AG_BIRTH = 0;
    public char SIZE = ' ';
    public String ORGIN = " ";
    public CICI9032_RCI_AREA[] RCI_AREA = new CICI9032_RCI_AREA[9];
    public CICI9032() {
        for (int i=0;i<9;i++) RCI_AREA[i] = new CICI9032_RCI_AREA();
    }
}
