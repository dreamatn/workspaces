package com.hisun.DD;

public class DDCFA01 {
    public int TOT_NUM = 0;
    public int REC_NUM = 0;
    public int END_POS = 0;
    public char END_FLG = ' ';
    public DDCFA01_DETAIL_DATA[] DETAIL_DATA = new DDCFA01_DETAIL_DATA[20];
    public DDCFA01() {
        for (int i=0;i<20;i++) DETAIL_DATA[i] = new DDCFA01_DETAIL_DATA();
    }
}
