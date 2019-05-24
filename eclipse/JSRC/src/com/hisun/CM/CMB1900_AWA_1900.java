package com.hisun.CM;

public class CMB1900_AWA_1900 {
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
    public char BAT_DR = ' ';
    public short BAT_DR_NO = 0;
    public char DR_SEQ = ' ';
    public short DR_SEQ_NO = 0;
    public CMB1900_ARRAY[] ARRAY = new CMB1900_ARRAY[5];
    public CMB1900_AWA_1900() {
        for (int i=0;i<5;i++) ARRAY[i] = new CMB1900_ARRAY();
    }
}
