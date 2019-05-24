package com.hisun.DD;

public class DDCSHQBP {
    public char FUNC = ' ';
    public String PRD_CD = " ";
    public String CHN_NAME = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public short PERIOD = 0;
    public char AUTO_FLG = ' ';
    public String CCY = " ";
    public double MIN_AMT = 0;
    public char CHK_PERD = ' ';
    public short CHK_DAY = 0;
    public char PST_PERD = ' ';
    public short PST_DATE = 0;
    public char CAL_COND = ' ';
    public char TIER_TYP = ' ';
    public DDCSHQBP_TIER_IR[] TIER_IR = new DDCSHQBP_TIER_IR[5];
    public DDCSHQBP() {
        for (int i=0;i<5;i++) TIER_IR[i] = new DDCSHQBP_TIER_IR();
    }
}
