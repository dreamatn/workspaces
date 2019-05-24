package com.hisun.DD;

public class DDB5980_AWA_5980 {
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
    public DDB5980_TIER_AMT[] TIER_AMT = new DDB5980_TIER_AMT[5];
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public DDB5980_AWA_5980() {
        for (int i=0;i<5;i++) TIER_AMT[i] = new DDB5980_TIER_AMT();
    }
}
