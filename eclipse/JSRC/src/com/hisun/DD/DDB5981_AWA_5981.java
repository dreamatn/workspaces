package com.hisun.DD;

public class DDB5981_AWA_5981 {
    public String PARM_CD = " ";
    public short PARM_CD_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public char CCY_TYP = ' ';
    public short CCY_TYP_NO = 0;
    public double MIN_AMT = 0;
    public short MIN_AMT_NO = 0;
    public int EFFDAT = 0;
    public short EFFDAT_NO = 0;
    public int EXPDAT = 0;
    public short EXPDAT_NO = 0;
    public char INT_TYP = ' ';
    public short INT_TYP_NO = 0;
    public char AGSP_FLG = ' ';
    public short AGSP_FLG_NO = 0;
    public DDB5981_TIER_AMT[] TIER_AMT = new DDB5981_TIER_AMT[5];
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String OD_RBSE = " ";
    public short OD_RBSE_NO = 0;
    public String OD_RCD = " ";
    public short OD_RCD_NO = 0;
    public double OD_SPRD = 0;
    public short OD_SPRD_NO = 0;
    public double OD_PCT = 0;
    public short OD_PCT_NO = 0;
    public double OD_RATE = 0;
    public short OD_RATE_NO = 0;
    public String UOD_RBSE = " ";
    public short UOD_RBSE_NO = 0;
    public String UOD_RCD = " ";
    public short UOD_RCD_NO = 0;
    public double UOD_SPRD = 0;
    public short UOD_SPRD_NO = 0;
    public double UOD_PCT = 0;
    public short UOD_PCT_NO = 0;
    public double UOD_RATE = 0;
    public short UOD_RATE_NO = 0;
    public String TOD_RBSE = " ";
    public short TOD_RBSE_NO = 0;
    public String TOD_RCD = " ";
    public short TOD_RCD_NO = 0;
    public double TOD_SPRD = 0;
    public short TOD_SPRD_NO = 0;
    public double TOD_PCT = 0;
    public short TOD_PCT_NO = 0;
    public double TOD_RATE = 0;
    public short TOD_RATE_NO = 0;
    public DDB5981_AWA_5981() {
        for (int i=0;i<5;i++) TIER_AMT[i] = new DDB5981_TIER_AMT();
    }
}
