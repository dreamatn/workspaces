package com.hisun.GD;

public class GDB1700_AWA_1700 {
    public String RSEQ = " ";
    public short RSEQ_NO = 0;
    public String CTA_NO = " ";
    public short CTA_NO_NO = 0;
    public String REF_NO = " ";
    public short REF_NO_NO = 0;
    public String R_TYP = " ";
    public short R_TYP_NO = 0;
    public short CNT = 0;
    public short CNT_NO = 0;
    public GDB1700_ARRY_CNT[] ARRY_CNT = new GDB1700_ARRY_CNT[10];
    public GDB1700_AWA_1700() {
        for (int i=0;i<10;i++) ARRY_CNT[i] = new GDB1700_ARRY_CNT();
    }
}
