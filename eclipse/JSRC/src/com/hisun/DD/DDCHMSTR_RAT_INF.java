package com.hisun.DD;

public class DDCHMSTR_RAT_INF {
    public DDCHMSTR_TIER_AMT[] TIER_AMT = new DDCHMSTR_TIER_AMT[5];
    public DDCHMSTR_RAT_INF() {
        for (int i=0;i<5;i++) TIER_AMT[i] = new DDCHMSTR_TIER_AMT();
    }
}
