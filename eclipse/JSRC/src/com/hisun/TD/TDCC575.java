package com.hisun.TD;

public class TDCC575 {
    public short TOTAL_PAGE = 0;
    public short TOTAL_NUM = 0;
    public short CURR_PAGE = 0;
    public char LAST_PAGE = ' ';
    public short PAGE_ROW = 0;
    public TDCC575_DATA[] DATA = new TDCC575_DATA[10];
    public TDCC575() {
        for (int i=0;i<10;i++) DATA[i] = new TDCC575_DATA();
    }
}
