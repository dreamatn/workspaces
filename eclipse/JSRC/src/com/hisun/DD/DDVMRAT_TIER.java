package com.hisun.DD;

public class DDVMRAT_TIER {
    public double TAMT = 0;
    public String TGRP = " ";
    public String TCLS = " ";
    public char SPR_TYPE = ' ';
    public DDVMRAT_TIER_IR[] TIER_IR = new DDVMRAT_TIER_IR[5];
    public char HL_FLG = ' ';
    public double MAX_RATE = 0;
    public double MIN_RATE = 0;
    public double FIX_RATE = 0;
    public DDVMRAT_TIER() {
        for (int i=0;i<5;i++) TIER_IR[i] = new DDVMRAT_TIER_IR();
    }
}
