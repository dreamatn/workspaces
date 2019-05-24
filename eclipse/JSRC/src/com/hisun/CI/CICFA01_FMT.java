package com.hisun.CI;

public class CICFA01_FMT {
    public String CI_NO = " ";
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public int PAGE_ROW = 0;
    public char LAST_PAGE = ' ';
    public CICFA01_DATA[] DATA = new CICFA01_DATA[30];
    public CICFA01_FMT() {
        for (int i=0;i<30;i++) DATA[i] = new CICFA01_DATA();
    }
}
