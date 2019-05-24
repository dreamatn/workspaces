package com.hisun.DD;

public class DDCOQACA {
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public char LAST_PAGE = ' ';
    public int PAGE_ROW = 0;
    public DDCOQACA_ACAC_INFO[] ACAC_INFO = new DDCOQACA_ACAC_INFO[10];
    public DDCOQACA() {
        for (int i=0;i<10;i++) ACAC_INFO[i] = new DDCOQACA_ACAC_INFO();
    }
}
