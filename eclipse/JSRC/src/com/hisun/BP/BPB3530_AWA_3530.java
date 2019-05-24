package com.hisun.BP;

public class BPB3530_AWA_3530 {
    public int MOV_DT = 0;
    public short MOV_DT_NO = 0;
    public long CONF_NO = 0;
    public short CONF_NO_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public String TLR = " ";
    public short TLR_NO = 0;
    public char BV_STS = ' ';
    public short BV_STS_NO = 0;
    public BPB3530_BV_DATA[] BV_DATA = new BPB3530_BV_DATA[10];
    public String ACNO = " ";
    public short ACNO_NO = 0;
    public char OUT_TYP = ' ';
    public short OUT_TYP_NO = 0;
    public char IN_TYP = ' ';
    public short IN_TYP_NO = 0;
    public char BV_FUNC = ' ';
    public short BV_FUNC_NO = 0;
    public String FEE_ACNO = " ";
    public short FEE_ACNO_NO = 0;
    public BPB3530_AWA_3530() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPB3530_BV_DATA();
    }
}
