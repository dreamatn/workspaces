package com.hisun.BP;

public class BPCIHIS {
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public int PAGE_ROW = 0;
    public char LAST_PAGE = ' ';
    public BPCIHIS_TIMES[] TIMES = new BPCIHIS_TIMES[20];
    public BPCIHIS() {
        for (int i=0;i<20;i++) TIMES[i] = new BPCIHIS_TIMES();
    }
}
