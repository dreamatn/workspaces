package com.hisun.CM;

public class CMCF811 {
    public short TOTAL_PAGE = 0;
    public short TOTAL_NUM = 0;
    public short CURR_PAGE = 0;
    public short PAGE_ROW = 0;
    public char LAST_PAGE = ' ';
    public CMCF811_DETAIL_INF[] DETAIL_INF = new CMCF811_DETAIL_INF[12];
    public CMCF811() {
        for (int i=0;i<12;i++) DETAIL_INF[i] = new CMCF811_DETAIL_INF();
    }
}
