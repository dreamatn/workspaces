package com.hisun.BP;

public class BPCITQ_DATA {
    public char FUNC = ' ';
    public int BR = 0;
    public int STR_DT = 0;
    public int STR_TM = 0;
    public int END_DT = 0;
    public int END_TM = 0;
    public BPCITQ_BUY_INFO BUY_INFO = new BPCITQ_BUY_INFO();
    public BPCITQ_SELL_INFO SELL_INFO = new BPCITQ_SELL_INFO();
    public double EX_RATE = 0;
    public String EX_CODE = " ";
    public BPCITQ_CCY_EXR_INFOR[] CCY_EXR_INFOR = new BPCITQ_CCY_EXR_INFOR[10];
    public short CMPL_CNT = 0;
    public short OUT_REC_CNT = 0;
    public char CONT_FLAG = ' ';
    public char CMPL_FLAG = ' ';
    public char EXR_FLG = ' ';
    public char B_CASH_FLG = ' ';
    public char S_CASH_FLG = ' ';
    public BPCITQ_DATA() {
        for (int i=0;i<10;i++) CCY_EXR_INFOR[i] = new BPCITQ_CCY_EXR_INFOR();
    }
}
