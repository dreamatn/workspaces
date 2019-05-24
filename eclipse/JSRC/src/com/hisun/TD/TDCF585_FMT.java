package com.hisun.TD;

public class TDCF585_FMT {
    public String AC_NO = " ";
    public int AC_SEQ = 0;
    public String MAIN_AC_O = " ";
    public String AC_SEQ_O = " ";
    public double OPEN_BAL = 0;
    public double LMT_BAL = 0;
    public int OPEN_DATE = 0;
    public String ACO_AC = " ";
    public short TOTAL_PAGE = 0;
    public short TOTAL_NUM = 0;
    public short CURR_PAGE = 0;
    public short PAGE_ROW = 0;
    public char LAST_PAGE = ' ';
    public TDCF585_DATA[] DATA = new TDCF585_DATA[10];
    public TDCF585_FMT() {
        for (int i=0;i<10;i++) DATA[i] = new TDCF585_DATA();
    }
}
