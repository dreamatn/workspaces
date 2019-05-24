package com.hisun.CI;

public class CICFB41 {
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public int PAGE_ROW = 0;
    public char LAST_PAGE = ' ';
    public CICFB41_DATA[] DATA = new CICFB41_DATA[10];
    public CICFB41() {
        for (int i=0;i<10;i++) DATA[i] = new CICFB41_DATA();
    }
}
