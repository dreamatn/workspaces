package com.hisun.BP;

public class BPB1000_AWA_1000 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String FEE_CD = " ";
    public short FEE_CD_NO = 0;
    public short FEE_NO = 0;
    public short FEE_NO_NO = 0;
    public String AREA = " ";
    public short AREA_NO = 0;
    public String FEE_CHNL = " ";
    public short FEE_CHNL_NO = 0;
    public String FERR_FMT = " ";
    public short FERR_FMT_NO = 0;
    public String REB_CD = " ";
    public short REB_CD_NO = 0;
    public double UP_AMT = 0;
    public short UP_AMT_NO = 0;
    public double DWN_AMT = 0;
    public short DWN_AMT_NO = 0;
    public double UP_PCT = 0;
    public short UP_PCT_NO = 0;
    public double DWN_PCT = 0;
    public short DWN_PCT_NO = 0;
    public String CHG_CPNT = " ";
    public short CHG_CPNT_NO = 0;
    public BPB1000_FAV_CODE[] FAV_CODE = new BPB1000_FAV_CODE[9];
    public char FAV_FLG = ' ';
    public short FAV_FLG_NO = 0;
    public String COM_CPNT = " ";
    public short COM_CPNT_NO = 0;
    public int LST_DATE = 0;
    public short LST_DATE_NO = 0;
    public String LST_TEL = " ";
    public short LST_TEL_NO = 0;
    public String LST_SUP1 = " ";
    public short LST_SUP1_NO = 0;
    public String LST_SUP2 = " ";
    public short LST_SUP2_NO = 0;
    public String TXT = " ";
    public short TXT_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public BPB1000_AWA_1000() {
        for (int i=0;i<9;i++) FAV_CODE[i] = new BPB1000_FAV_CODE();
    }
}
