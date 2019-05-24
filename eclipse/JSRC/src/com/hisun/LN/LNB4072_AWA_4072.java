package com.hisun.LN;

public class LNB4072_AWA_4072 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public char CTA_TYP = ' ';
    public short CTA_TYP_NO = 0;
    public String CTA_NO = " ";
    public short CTA_NO_NO = 0;
    public int EE_DATE = 0;
    public short EE_DATE_NO = 0;
    public int VAL_DATE = 0;
    public short VAL_DATE_NO = 0;
    public double EXT_AMT = 0;
    public short EXT_AMT_NO = 0;
    public String REASON = " ";
    public short REASON_NO = 0;
    public char STATUS = ' ';
    public short STATUS_NO = 0;
    public char RFLT_FLG = ' ';
    public short RFLT_FLG_NO = 0;
    public int NFLT_DT = 0;
    public short NFLT_DT_NO = 0;
    public short FLT_PERD = 0;
    public short FLT_PERD_NO = 0;
    public char FLT_PUNT = ' ';
    public short FLT_PUNT_NO = 0;
    public char COMP_MTH = ' ';
    public short COMP_MTH_NO = 0;
    public LNB4072_RAT_DATA[] RAT_DATA = new LNB4072_RAT_DATA[3];
    public char FLT_MTH = ' ';
    public short FLT_MTH_NO = 0;
    public double COST_RAT = 0;
    public short COST_RAT_NO = 0;
    public String RAT_MEMO = " ";
    public short RAT_MEMO_NO = 0;
    public double PREM_RAT = 0;
    public short PREM_RAT_NO = 0;
    public double ADD_RAT = 0;
    public short ADD_RAT_NO = 0;
    public double ALL_RAT = 0;
    public short ALL_RAT_NO = 0;
    public LNB4072_OLD_RAT[] OLD_RAT = new LNB4072_OLD_RAT[3];
    public LNB4072_AWA_4072() {
        for (int i=0;i<3;i++) RAT_DATA[i] = new LNB4072_RAT_DATA();
        for (int i=0;i<3;i++) OLD_RAT[i] = new LNB4072_OLD_RAT();
    }
}
