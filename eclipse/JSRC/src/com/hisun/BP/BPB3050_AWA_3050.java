package com.hisun.BP;

public class BPB3050_AWA_3050 {
    public BPB3050_BV_DATA[] BV_DATA = new BPB3050_BV_DATA[10];
    public String TLR = " ";
    public short TLR_NO = 0;
    public char PSW_TYP = ' ';
    public short PSW_TYP_NO = 0;
    public String TLRC_PSW = " ";
    public short TLRC_PSW_NO = 0;
    public String TLRK_PSW = " ";
    public short TLRK_PSW_NO = 0;
    public char BV_FUNC = ' ';
    public short BV_FUNC_NO = 0;
    public String REP_TLR = " ";
    public short REP_TLR_NO = 0;
    public BPB3050_AWA_3050() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPB3050_BV_DATA();
    }
}
