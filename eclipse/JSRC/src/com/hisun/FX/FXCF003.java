package com.hisun.FX;

public class FXCF003 {
    public int CURR_MAX_ROW = 0;
    public char END_FLG = ' ';
    public FXCF003_OUT_INFO[] OUT_INFO = new FXCF003_OUT_INFO[30];
    public FXCF003() {
        for (int i=0;i<30;i++) OUT_INFO[i] = new FXCF003_OUT_INFO();
    }
}
