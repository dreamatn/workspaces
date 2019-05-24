package com.hisun.BP;

public class BPB3210_AWA_3210 {
    public BPB3210_BV_DATA[] BV_DATA = new BPB3210_BV_DATA[4];
    public int BR = 0;
    public short BR_NO = 0;
    public String TLR = " ";
    public short TLR_NO = 0;
    public int MOV_DT = 0;
    public short MOV_DT_NO = 0;
    public long CNF_NO = 0;
    public short CNF_NO_NO = 0;
    public char PSW_TYP = ' ';
    public short PSW_TYP_NO = 0;
    public String TLRC_PSW = " ";
    public short TLRC_PSW_NO = 0;
    public String TLRK_PSW = " ";
    public short TLRK_PSW_NO = 0;
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public int TX_BR = 0;
    public short TX_BR_NO = 0;
    public String TX_TLR = " ";
    public short TX_TLR_NO = 0;
    public BPB3210_AWA_3210() {
        for (int i=0;i<4;i++) BV_DATA[i] = new BPB3210_BV_DATA();
    }
}
