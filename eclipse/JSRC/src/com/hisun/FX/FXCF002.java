package com.hisun.FX;

public class FXCF002 {
    public int CURR_MAX_ROW = 0;
    public char END_FLG = ' ';
    public FXCF002_OUT_INFO[] OUT_INFO = new FXCF002_OUT_INFO[5];
    public FXCF002() {
        for (int i=0;i<5;i++) OUT_INFO[i] = new FXCF002_OUT_INFO();
    }
}
