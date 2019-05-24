package com.hisun.LN;

public class LNCI5111 {
    public String PAPER_NO = " ";
    public String PAPER_NM = " ";
    public String CCY = " ";
    public double AMT = 0;
    public int MAIN_BR = 0;
    public char SYN_TYP = ' ';
    public LNCI5111_PART_INF[] PART_INF = new LNCI5111_PART_INF[10];
    public LNCI5111() {
        for (int i=0;i<10;i++) PART_INF[i] = new LNCI5111_PART_INF();
    }
}
