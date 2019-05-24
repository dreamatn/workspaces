package com.hisun.DD;

public class DDCHHQBP {
    public short ADP_PERIOD = 0;
    public char AUTO_FLG = ' ';
    public String CCY = " ";
    public double MIN_AMT = 0;
    public char CHECK_PERIOD = ' ';
    public short CHECK_DAYS = 0;
    public char POST_PERIOD = ' ';
    public short POST_DATE = 0;
    public char INT_CAL_COND = ' ';
    public char TIER_TYPE = ' ';
    public DDCHHQBP_TIER_IR[] TIER_IR = new DDCHHQBP_TIER_IR[5];
    public DDCHHQBP() {
        for (int i=0;i<5;i++) TIER_IR[i] = new DDCHHQBP_TIER_IR();
    }
}
