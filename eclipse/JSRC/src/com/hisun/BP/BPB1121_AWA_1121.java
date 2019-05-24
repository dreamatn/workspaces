package com.hisun.BP;

public class BPB1121_AWA_1121 {
    public String FEE_CD = " ";
    public short FEE_CD_NO = 0;
    public String ORG_CD = " ";
    public short ORG_CD_NO = 0;
    public String CHNL_NO = " ";
    public short CHNL_NO_NO = 0;
    public String REF_CCY = " ";
    public short REF_CCY_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public double SAT_AMT = 0;
    public short SAT_AMT_NO = 0;
    public String FEE_CCY = " ";
    public short FEE_CCY_NO = 0;
    public double MIN_AMT = 0;
    public short MIN_AMT_NO = 0;
    public double MAX_AMT = 0;
    public short MAX_AMT_NO = 0;
    public char AGG_TYP = ' ';
    public short AGG_TYP_NO = 0;
    public double FIX_AMT = 0;
    public short FIX_AMT_NO = 0;
    public char CLA_TYP = ' ';
    public short CLA_TYP_NO = 0;
    public BPB1121_RAT_INFO[] RAT_INFO = new BPB1121_RAT_INFO[10];
    public BPB1121_AWA_1121() {
        for (int i=0;i<10;i++) RAT_INFO[i] = new BPB1121_RAT_INFO();
    }
}
