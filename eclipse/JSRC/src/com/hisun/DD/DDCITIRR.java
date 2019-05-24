package com.hisun.DD;

public class DDCITIRR {
    public DDCITIRR_RC RC = new DDCITIRR_RC();
    public char TYPE = ' ';
    public DDCITIRR_RBASE_INFO[] RBASE_INFO = new DDCITIRR_RBASE_INFO[5];
    public char HL_FLAG = ' ';
    public double FIX_RATE = 0;
    public double MAX_RATE = 0;
    public double MIN_RATE = 0;
    public String CCY = " ";
    public int AC_DATE = 0;
    public int BR = 0;
    public String AC_NO = " ";
    public String RUL_CD = " ";
    public String CI_NO = " ";
    public double COMPUTED_RATE = 0;
    public DDCITIRR_RATE_INFO[] RATE_INFO = new DDCITIRR_RATE_INFO[5];
    public DDCITIRR() {
        for (int i=0;i<5;i++) RBASE_INFO[i] = new DDCITIRR_RBASE_INFO();
        for (int i=0;i<5;i++) RATE_INFO[i] = new DDCITIRR_RATE_INFO();
    }
}
