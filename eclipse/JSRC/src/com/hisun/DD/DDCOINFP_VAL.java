package com.hisun.DD;

public class DDCOINFP_VAL {
    public char TIER_TYPE = ' ';
    public char AMT_TIER_TYPE = ' ';
    public DDCOINFP_TIER_AMT[] TIER_AMT = new DDCOINFP_TIER_AMT[5];
    public String OD_INT_RBAS = " ";
    public String OD_INT_RCD = " ";
    public double OD_SPR = 0;
    public String UOD_INT_RBAS = " ";
    public String UOD_INT_RCD = " ";
    public double UOD_SPR = 0;
    public String TOD_INT_RBAS = " ";
    public String TOD_INT_RCD = " ";
    public double TOD_SPR = 0;
    public DDCOINFP_VAL() {
        for (int i=0;i<5;i++) TIER_AMT[i] = new DDCOINFP_TIER_AMT();
    }
}
