package com.hisun.DD;

public class DDVMRAT_VAL {
    public double MIN_AMT = 0;
    public char TIER_TYPE = ' ';
    public char AMT_TIER_TYPE = ' ';
    public DDVMRAT_TIER[] TIER = new DDVMRAT_TIER[5];
    public String OD_INT_RBAS = " ";
    public String OD_INT_RCD = " ";
    public double OD_SPR = 0;
    public double OD_PCT = 0;
    public double OD_RATE = 0;
    public String UOD_INT_RBAS = " ";
    public String UOD_INT_RCD = " ";
    public double UOD_SPR = 0;
    public double UOD_PCT = 0;
    public double UOD_RATE = 0;
    public String TOD_INT_RBAS = " ";
    public String TOD_INT_RCD = " ";
    public double TOD_SPR = 0;
    public double TOD_PCT = 0;
    public double TOD_RATE = 0;
    public DDVMRAT_VAL() {
        for (int i=0;i<5;i++) TIER[i] = new DDVMRAT_TIER();
    }
}
