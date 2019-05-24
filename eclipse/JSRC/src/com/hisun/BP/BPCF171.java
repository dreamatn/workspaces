package com.hisun.BP;

public class BPCF171 {
    public int TOTAL_ROW = 0;
    public int CURR_MAX_ROW = 0;
    public char END_FLG = ' ';
    public BPCF171_DATA[] DATA = new BPCF171_DATA[50];
    public BPCF171() {
        for (int i=0;i<50;i++) DATA[i] = new BPCF171_DATA();
    }
}
