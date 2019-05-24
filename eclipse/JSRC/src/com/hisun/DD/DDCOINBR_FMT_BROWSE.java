package com.hisun.DD;

public class DDCOINBR_FMT_BROWSE {
    public int FMT_B_TOTAL_PAGE = 0;
    public int FMT_B_TOTAL_NUM = 0;
    public int FMT_B_CURR_PAGE = 0;
    public char FMT_B_LAST_PAGE = ' ';
    public int FMT_B_PAGE_ROW = 0;
    public String FMT_B_P_AC = " ";
    public String FMT_B_P_AC_NM = " ";
    public char FILLER10 = 0X02;
    public char FMT_B_CINT_FLG = ' ';
    public DDCOINBR_FMT_B_DATA[] FMT_B_DATA = new DDCOINBR_FMT_B_DATA[5];
    public DDCOINBR_FMT_BROWSE() {
        for (int i=0;i<5;i++) FMT_B_DATA[i] = new DDCOINBR_FMT_B_DATA();
    }
}
