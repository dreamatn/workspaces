package com.hisun.BP;

public class BPCUBVRV {
    public char FUNC = ' ';
    public String TLR = " ";
    public char TYPE = ' ';
    public String BV_CODE = " ";
    public String CCY = " ";
    public double PVAL = 0;
    public String HEAD_NO = " ";
    public String BEG_NO = " ";
    public String END_NO = " ";
    public int NUM = 0;
    public double AMT = 0;
    public char VB_FLG = ' ';
    public int CNT = 0;
    public BPCUBVRV_DATA_INFO[] DATA_INFO = new BPCUBVRV_DATA_INFO[99];
    public BPCUBVRV() {
        for (int i=0;i<99;i++) DATA_INFO[i] = new BPCUBVRV_DATA_INFO();
    }
}
