package com.hisun.DC;

public class DCB3115_AWA_3115 {
    public String CARD_NO = " ";
    public short CARD_NO_NO = 0;
    public char CROS_FLG = ' ';
    public short CROS_FLG_NO = 0;
    public char HDAC_FLG = ' ';
    public short HDAC_FLG_NO = 0;
    public char CHK_FLG = ' ';
    public short CHK_FLG_NO = 0;
    public int MAX_SUP = 0;
    public short MAX_SUP_NO = 0;
    public String TEL_NO = " ";
    public short TEL_NO_NO = 0;
    public String DWEL_PLC = " ";
    public short DWEL_PLC_NO = 0;
    public String EMAL = " ";
    public short EMAL_NO = 0;
    public String POST_CD = " ";
    public short POST_CD_NO = 0;
    public char LMT_FLG = ' ';
    public short LMT_FLG_NO = 0;
    public char UPD_TYP = ' ';
    public short UPD_TYP_NO = 0;
    public DCB3115_A_DATE[] A_DATE = new DCB3115_A_DATE[3];
    public DCB3115_AWA_3115() {
        for (int i=0;i<3;i++) A_DATE[i] = new DCB3115_A_DATE();
    }
}
