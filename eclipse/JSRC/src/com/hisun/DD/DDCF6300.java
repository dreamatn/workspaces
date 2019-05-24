package com.hisun.DD;

public class DDCF6300 {
    public short TOT_PAGE = 0;
    public short TOT_NUM = 0;
    public short CURR_PAGE = 0;
    public char LAST_IND = ' ';
    public short CURR_ROW = 0;
    public DDCF6300_OUTPUT_DATA[] OUTPUT_DATA = new DDCF6300_OUTPUT_DATA[10];
    public DDCF6300() {
        for (int i=0;i<10;i++) OUTPUT_DATA[i] = new DDCF6300_OUTPUT_DATA();
    }
}
