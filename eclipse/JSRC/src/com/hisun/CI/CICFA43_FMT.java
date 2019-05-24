package com.hisun.CI;

public class CICFA43_FMT {
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public int PAGE_ROW = 0;
    public char LAST_PAGE = ' ';
    public CICFA43_DATA[] DATA = new CICFA43_DATA[10];
    public CICFA43_FMT() {
        for (int i=0;i<10;i++) DATA[i] = new CICFA43_DATA();
    }
}
