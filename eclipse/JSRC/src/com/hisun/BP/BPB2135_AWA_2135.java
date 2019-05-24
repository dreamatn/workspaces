package com.hisun.BP;

public class BPB2135_AWA_2135 {
    public int MOV_DT = 0;
    public short MOV_DT_NO = 0;
    public int CONF_NO = 0;
    public short CONF_NO_NO = 0;
    public int OUT_BR = 0;
    public short OUT_BR_NO = 0;
    public String OUT_TLR = " ";
    public short OUT_TLR_NO = 0;
    public char OUT_FLG = ' ';
    public short OUT_FLG_NO = 0;
    public char IN_FLG = ' ';
    public short IN_FLG_NO = 0;
    public BPB2135_CCY_INF[] CCY_INF = new BPB2135_CCY_INF[5];
    public BPB2135_AWA_2135() {
        for (int i=0;i<5;i++) CCY_INF[i] = new BPB2135_CCY_INF();
    }
}
