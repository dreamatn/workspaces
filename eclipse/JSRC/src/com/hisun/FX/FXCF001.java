package com.hisun.FX;

public class FXCF001 {
    public int CURR_MAX_ROW = 0;
    public char END_FLG = ' ';
    public FXCF001_OUT_INFO[] OUT_INFO = new FXCF001_OUT_INFO[10];
    public FXCF001() {
        for (int i=0;i<10;i++) OUT_INFO[i] = new FXCF001_OUT_INFO();
    }
}
