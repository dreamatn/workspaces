package com.hisun.CI;

public class CICOVAL {
    public char FUNC = ' ';
    public int LC_NO = 0;
    public int SEQ = 0;
    public short CON_SEQ = 0;
    public CICOVAL_TIMES[] TIMES = new CICOVAL_TIMES[60];
    public char END_FLG = ' ';
    public CICOVAL() {
        for (int i=0;i<60;i++) TIMES[i] = new CICOVAL_TIMES();
    }
}
