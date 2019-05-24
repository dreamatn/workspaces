package com.hisun.TD;

public class TDCOACE {
    public TDCOACE_PAGE_INF PAGE_INF = new TDCOACE_PAGE_INF();
    public TDCOACE_DATA[] DATA = new TDCOACE_DATA[6];
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public char LAST_PAGE = ' ';
    public int PAGE_ROW = 0;
    public double GROP_AMT = 0;
    public char FILLER111 = 0X01;
    public double GROP_INT = 0;
    public char FILLER113 = 0X01;
    public TDCOACE() {
        for (int i=0;i<6;i++) DATA[i] = new TDCOACE_DATA();
    }
}
