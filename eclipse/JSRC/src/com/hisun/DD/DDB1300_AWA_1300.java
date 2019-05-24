package com.hisun.DD;

public class DDB1300_AWA_1300 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String PRD_CD = " ";
    public short PRD_CD_NO = 0;
    public String CHN_NAME = " ";
    public short CHN_NAME_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public short PERIOD = 0;
    public short PERIOD_NO = 0;
    public char AUTO_FLG = ' ';
    public short AUTO_FLG_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public double MIN_AMT = 0;
    public short MIN_AMT_NO = 0;
    public char CHK_PERD = ' ';
    public short CHK_PERD_NO = 0;
    public short CHK_DAY = 0;
    public short CHK_DAY_NO = 0;
    public char PST_PERD = ' ';
    public short PST_PERD_NO = 0;
    public short PST_DATE = 0;
    public short PST_DATE_NO = 0;
    public char CAL_COND = ' ';
    public short CAL_COND_NO = 0;
    public char TIER_TYP = ' ';
    public short TIER_TYP_NO = 0;
    public DDB1300_TIER_IR[] TIER_IR = new DDB1300_TIER_IR[5];
    public DDB1300_AWA_1300() {
        for (int i=0;i<5;i++) TIER_IR[i] = new DDB1300_TIER_IR();
    }
}
