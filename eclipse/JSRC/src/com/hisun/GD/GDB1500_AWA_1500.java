package com.hisun.GD;

public class GDB1500_AWA_1500 {
    public String RSEQ = " ";
    public short RSEQ_NO = 0;
    public String CTA_NO = " ";
    public short CTA_NO_NO = 0;
    public String REF_NO = " ";
    public short REF_NO_NO = 0;
    public String R_TYP = " ";
    public short R_TYP_NO = 0;
    public int EXP_DT = 0;
    public short EXP_DT_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public GDB1500_CPRL_CNT[] CPRL_CNT = new GDB1500_CPRL_CNT[5];
    public GDB1500_AWA_1500() {
        for (int i=0;i<5;i++) CPRL_CNT[i] = new GDB1500_CPRL_CNT();
    }
}
