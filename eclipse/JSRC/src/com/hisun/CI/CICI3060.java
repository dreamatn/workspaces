package com.hisun.CI;

public class CICI3060 {
    public char FUNC = ' ';
    public int LC_NO = 0;
    public int SEQ = 0;
    public short CON_SEQ = 0;
    public CICI3060_TIMES[] TIMES = new CICI3060_TIMES[60];
    public char END_FLG = ' ';
    public CICI3060() {
        for (int i=0;i<60;i++) TIMES[i] = new CICI3060_TIMES();
    }
}
