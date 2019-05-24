package com.hisun.DD;

public class DDCOCHQP {
    public short TOT_PAGE = 0;
    public short TOT_NUM = 0;
    public short CURR_PAGE = 0;
    public char LAST_IND = ' ';
    public short CURR_ROW = 0;
    public DDCOCHQP_OUTPUT_DATA[] OUTPUT_DATA = new DDCOCHQP_OUTPUT_DATA[20];
    public DDCOCHQP() {
        for (int i=0;i<20;i++) OUTPUT_DATA[i] = new DDCOCHQP_OUTPUT_DATA();
    }
}
