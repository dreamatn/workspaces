package com.hisun.LN;

public class LNCI4072 {
    public char FUNC = ' ';
    public char CTA_TYP = ' ';
    public String CTA_NO = " ";
    public int EE_DATE = 0;
    public int VAL_DATE = 0;
    public double EXT_AMT = 0;
    public String REASON = " ";
    public char STATUS = ' ';
    public char RFLT_FLG = ' ';
    public int NFLT_DT = 0;
    public short FLT_PERD = 0;
    public char FLT_PUNT = ' ';
    public char COMP_MTH = ' ';
    public LNCI4072_RAT_DATA[] RAT_DATA = new LNCI4072_RAT_DATA[3];
    public char FLT_MTH = ' ';
    public double COST_RAT = 0;
    public String RAT_MEMO = " ";
    public double PREM_RAT = 0;
    public double ADD_RAT = 0;
    public double ALL_RAT = 0;
    public LNCI4072_OLD_RAT[] OLD_RAT = new LNCI4072_OLD_RAT[3];
    public LNCI4072() {
        for (int i=0;i<3;i++) RAT_DATA[i] = new LNCI4072_RAT_DATA();
        for (int i=0;i<3;i++) OLD_RAT[i] = new LNCI4072_OLD_RAT();
    }
}
