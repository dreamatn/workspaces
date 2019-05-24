package com.hisun.CI;

public class CIB3060_AWA_3060 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public int LC_NO = 0;
    public short LC_NO_NO = 0;
    public int SEQ = 0;
    public short SEQ_NO = 0;
    public short CON_SEQ = 0;
    public short CON_SEQ_NO = 0;
    public CIB3060_TIMES[] TIMES = new CIB3060_TIMES[60];
    public char END_FLG = ' ';
    public short END_FLG_NO = 0;
    public CIB3060_AWA_3060() {
        for (int i=0;i<60;i++) TIMES[i] = new CIB3060_TIMES();
    }
}
