package com.hisun.BP;

public class BPB5130_AWA_5130 {
    public char FUNC_CD = ' ';
    public short FUNC_CD_NO = 0;
    public char TYPE = ' ';
    public short TYPE_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String BASE_TYP = " ";
    public short BASE_TYP_NO = 0;
    public String TENOR = " ";
    public short TENOR_NO = 0;
    public char FMT = ' ';
    public short FMT_NO = 0;
    public BPB5130_DATA[] DATA = new BPB5130_DATA[10];
    public char FLG = ' ';
    public short FLG_NO = 0;
    public BPB5130_AWA_5130() {
        for (int i=0;i<10;i++) DATA[i] = new BPB5130_DATA();
    }
}
