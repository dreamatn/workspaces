package com.hisun.DD;

public class DDCICKCD_OUTPUT_DATA {
    public short RTN_CNT = 0;
    public DDCICKCD_RC[] RC = new DDCICKCD_RC[10];
    public char OD_FAC = ' ';
    public DDCICKCD_OUTPUT_DATA() {
        for (int i=0;i<10;i++) RC[i] = new DDCICKCD_RC();
    }
}
