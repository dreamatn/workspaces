package com.hisun.AI;

public class AIB5500_AWA_5500 {
    public int BR = 0;
    public short BR_NO = 0;
    public String ITM = " ";
    public short ITM_NO = 0;
    public int SEQ = 0;
    public short SEQ_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String RVS_NO = " ";
    public short RVS_NO_NO = 0;
    public double STR_AMT = 0;
    public short STR_AMT_NO = 0;
    public double END_AMT = 0;
    public short END_AMT_NO = 0;
    public int STR_DATE = 0;
    public short STR_DATE_NO = 0;
    public int END_DATE = 0;
    public short END_DATE_NO = 0;
    public int EXP_STDT = 0;
    public short EXP_STDT_NO = 0;
    public int EXP_ENDT = 0;
    public short EXP_ENDT_NO = 0;
    public char STS = ' ';
    public short STS_NO = 0;
    public int RVS_SEQ = 0;
    public short RVS_SEQ_NO = 0;
    public AIB5500_RVS_DATA[] RVS_DATA = new AIB5500_RVS_DATA[15];
    public String ITM1 = " ";
    public short ITM1_NO = 0;
    public int SEQ1 = 0;
    public short SEQ1_NO = 0;
    public short SUS_TERM = 0;
    public short SUS_TERM_NO = 0;
    public int SUS_STDT = 0;
    public short SUS_STDT_NO = 0;
    public int SUS_ENDT = 0;
    public short SUS_ENDT_NO = 0;
    public int PAGE_ROW = 0;
    public short PAGE_ROW_NO = 0;
    public int PAGE_NUM = 0;
    public short PAGE_NUM_NO = 0;
    public double C_BAL1 = 0;
    public short C_BAL1_NO = 0;
    public AIB5500_AWA_5500() {
        for (int i=0;i<15;i++) RVS_DATA[i] = new AIB5500_RVS_DATA();
    }
}
