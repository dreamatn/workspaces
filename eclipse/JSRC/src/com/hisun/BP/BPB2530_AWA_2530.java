package com.hisun.BP;

public class BPB2530_AWA_2530 {
    public String TLR = " ";
    public short TLR_NO = 0;
    public BPB2530_CCY_INFO[] CCY_INFO = new BPB2530_CCY_INFO[20];
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public String TR_TLR = " ";
    public short TR_TLR_NO = 0;
    public String CONF_INF = " ";
    public short CONF_INF_NO = 0;
    public BPB2530_AWA_2530() {
        for (int i=0;i<20;i++) CCY_INFO[i] = new BPB2530_CCY_INFO();
    }
}
