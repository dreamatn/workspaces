package com.hisun.SO;

public class SOCIOA {
    public String SYS_ID = " ";
    public String VER_NO = " ";
    public char SYS_DEST = ' ';
    public String ENV_ID = " ";
    public char REQ_TYPE = ' ';
    public String AP_MMO = " ";
    public String AP_EXT_MMO = " ";
    public char MSG_TYPE = ' ';
    public SOCIOA_MSG_ID MSG_ID = new SOCIOA_MSG_ID();
    public short CURS_POS = 0;
    public int JRN_NO = 0;
    public String CHNL = " ";
    public String SERV_ID = " ";
    public String TR_ID = " ";
    public int AC_DATE = 0;
    public String TERM_ID = " ";
    public int TR_DATE = 0;
    public int TR_TIME = 0;
    public String TL_ID = " ";
    public short TR_BANK = 0;
    public SOCIOA_AUTH_RESN_TBL[] AUTH_RESN_TBL = new SOCIOA_AUTH_RESN_TBL[30];
    public String SUP1_ID = " ";
    public String SUP2_ID = " ";
    public String SUP1_PSW = " ";
    public String SUP2_PSW = " ";
    public short AUTH_LVL1 = 0;
    public short AUTH_LVL2 = 0;
    public char AUTH_IND = ' ';
    public String SESS_NO = " ";
    public char PAGE_IND = ' ';
    public int SUBS_TR_ID = 0;
    public Object GWA_PTR;
    public String TR_CLASS = " ";
    public String FILLER = " ";
    public String INP_DATA = " ";
    public SOCIOA_OUTP_AREA OUTP_AREA = new SOCIOA_OUTP_AREA();
    public SOCIOA() {
        for (int i=0;i<30;i++) AUTH_RESN_TBL[i] = new SOCIOA_AUTH_RESN_TBL();
    }
}
