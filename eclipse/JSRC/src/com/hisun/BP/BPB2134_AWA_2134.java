package com.hisun.BP;

public class BPB2134_AWA_2134 {
    public int OUT_DT = 0;
    public short OUT_DT_NO = 0;
    public int OUT_BR = 0;
    public short OUT_BR_NO = 0;
    public char OUT_FLG = ' ';
    public short OUT_FLG_NO = 0;
    public char IN_FLG = ' ';
    public short IN_FLG_NO = 0;
    public int IN_BR = 0;
    public short IN_BR_NO = 0;
    public String IN_TLR = " ";
    public short IN_TLR_NO = 0;
    public BPB2134_CCY_INF[] CCY_INF = new BPB2134_CCY_INF[5];
    public int CONF_NO = 0;
    public short CONF_NO_NO = 0;
    public String OUT_TLR = " ";
    public short OUT_TLR_NO = 0;
    public BPB2134_AWA_2134() {
        for (int i=0;i<5;i++) CCY_INF[i] = new BPB2134_CCY_INF();
    }
}
