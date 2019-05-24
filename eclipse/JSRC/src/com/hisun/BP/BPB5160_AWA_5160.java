package com.hisun.BP;

public class BPB5160_AWA_5160 {
    public String EXR_TYPE = " ";
    public short EXR_TYPE_NO = 0;
    public int EFF_DT = 0;
    public short EFF_DT_NO = 0;
    public int EFF_TM = 0;
    public short EFF_TM_NO = 0;
    public int IPT_DT = 0;
    public short IPT_DT_NO = 0;
    public int IPT_TM = 0;
    public short IPT_TM_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public BPB5160_CCY_INFO[] CCY_INFO = new BPB5160_CCY_INFO[20];
    public char CONT_FLG = ' ';
    public short CONT_FLG_NO = 0;
    public short CMPL_CNT = 0;
    public short CMPL_CNT_NO = 0;
    public short OUT_CNT = 0;
    public short OUT_CNT_NO = 0;
    public char CMPL_FLG = ' ';
    public short CMPL_FLG_NO = 0;
    public BPB5160_AWA_5160() {
        for (int i=0;i<20;i++) CCY_INFO[i] = new BPB5160_CCY_INFO();
    }
}
