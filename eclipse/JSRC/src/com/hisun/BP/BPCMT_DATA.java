package com.hisun.BP;

public class BPCMT_DATA {
    public char FUNC = ' ';
    public String EXR_TYPE = " ";
    public int BR = 0;
    public int EFF_DT = 0;
    public int EFF_TM = 0;
    public int IPT_DT = 0;
    public int IPT_TM = 0;
    public BPCMT_CCY_INFO[] CCY_INFO = new BPCMT_CCY_INFO[20];
    public char CONT_FLAG = ' ';
    public short CMPL_CNT = 0;
    public short OUT_REC_CNT = 0;
    public char CMPL_FLAG = ' ';
    public BPCMT_DATA() {
        for (int i=0;i<20;i++) CCY_INFO[i] = new BPCMT_CCY_INFO();
    }
}
