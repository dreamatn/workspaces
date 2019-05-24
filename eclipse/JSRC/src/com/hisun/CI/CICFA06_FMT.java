package com.hisun.CI;

public class CICFA06_FMT {
    public String CI_NO = " ";
    public String ID_TYP = " ";
    public String ID_NO = " ";
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public int PAGE_ROW = 0;
    public char LAST_PAGE = ' ';
    public CICFA06_DATA[] DATA = new CICFA06_DATA[5];
    public CICFA06_FMT() {
        for (int i=0;i<5;i++) DATA[i] = new CICFA06_DATA();
    }
}
