package com.hisun.BP;

public class BPB3029_AWA_3029 {
    public int BR = 0;
    public short BR_NO = 0;
    public String TLR = " ";
    public short TLR_NO = 0;
    public char BV_STS = ' ';
    public short BV_STS_NO = 0;
    public BPB3029_BV_DATA[] BV_DATA = new BPB3029_BV_DATA[10];
    public char BV_FUNC = ' ';
    public short BV_FUNC_NO = 0;
    public int MOV_DT = 0;
    public short MOV_DT_NO = 0;
    public long CONF_NO = 0;
    public short CONF_NO_NO = 0;
    public int APP_NO = 0;
    public short APP_NO_NO = 0;
    public String CONF_NOG = " ";
    public short CONF_NOG_NO = 0;
    public String APP_NO_G = " ";
    public short APP_NO_G_NO = 0;
    public BPB3029_AWA_3029() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPB3029_BV_DATA();
    }
}