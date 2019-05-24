package com.hisun.BP;

public class BPB3030_AWA_3030 {
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
    public BPB3030_BV_DATA[] BV_DATA = new BPB3030_BV_DATA[10];
    public String ACNO = " ";
    public short ACNO_NO = 0;
    public int APP_NO = 0;
    public short APP_NO_NO = 0;
    public String APP_NO_G = " ";
    public short APP_NO_G_NO = 0;
    public BPB3030_AWA_3030() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPB3030_BV_DATA();
    }
}
