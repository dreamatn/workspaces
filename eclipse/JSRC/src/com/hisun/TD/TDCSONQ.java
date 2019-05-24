package com.hisun.TD;

public class TDCSONQ {
    public String PROD_NAME = " ";
    public char FILLER2 = 0X02;
    public short PART_NUM = 0;
    public TDCSONQ_DATA[] DATA = new TDCSONQ_DATA[10];
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public char LAST_PAGE = ' ';
    public int PAGE_ROW = 0;
    public TDCSONQ() {
        for (int i=0;i<10;i++) DATA[i] = new TDCSONQ_DATA();
    }
}
