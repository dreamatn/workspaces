package com.hisun.FX;

public class FXCF156 {
    public char END_FLG = ' ';
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public int PAGE_ROW = 0;
    public FXCF156_OUT_INFO[] OUT_INFO = new FXCF156_OUT_INFO[30];
    public FXCF156() {
        for (int i=0;i<30;i++) OUT_INFO[i] = new FXCF156_OUT_INFO();
    }
}
