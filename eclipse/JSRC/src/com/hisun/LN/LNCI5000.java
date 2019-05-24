package com.hisun.LN;

public class LNCI5000 {
    public char FUN_CD = ' ';
    public String LN_CTANO = " ";
    public String LN_AC = " ";
    public String PAPER_NM = " ";
    public String CCY = " ";
    public double AMT = 0;
    public int MAIN_BR = 0;
    public char SYN_TYP = ' ';
    public LNCI5000_PART_INF[] PART_INF = new LNCI5000_PART_INF[10];
    public LNCI5000() {
        for (int i=0;i<10;i++) PART_INF[i] = new LNCI5000_PART_INF();
    }
}
