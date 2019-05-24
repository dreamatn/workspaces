package com.hisun.DD;

public class DDCF7720 {
    public short TOT_PAGE = 0;
    public short TOT_NUM = 0;
    public short CURR_PAGE = 0;
    public char LAST_IND = ' ';
    public short CURR_ROW = 0;
    public DDCF7720_OUTPUT_DATA[] OUTPUT_DATA = new DDCF7720_OUTPUT_DATA[8];
    public DDCF7720() {
        for (int i=0;i<8;i++) OUTPUT_DATA[i] = new DDCF7720_OUTPUT_DATA();
    }
}
