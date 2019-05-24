package com.hisun.CI;

public class CICQVAC_OUT_DATA {
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public int CURR_PAGE_ROW = 0;
    public char END_FLG = ' ';
    public CICQVAC_REL_INF[] REL_INF = new CICQVAC_REL_INF[100];
    public CICQVAC_OUT_DATA() {
        for (int i=0;i<100;i++) REL_INF[i] = new CICQVAC_REL_INF();
    }
}
