package com.hisun.CI;

public class CICFA44 {
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public int PAGE_ROW = 0;
    public char LAST_PAGE = ' ';
    public CICFA44_DATA[] DATA = new CICFA44_DATA[10];
    public CICFA44() {
        for (int i=0;i<10;i++) DATA[i] = new CICFA44_DATA();
    }
}
