package com.hisun.LN;

public class LNCSPART_DATA {
    public String LN_CTANO = " ";
    public String LN_AC = " ";
    public String PAPER_NM = " ";
    public String CCY = " ";
    public double AMT = 0;
    public int MAIN_BR = 0;
    public char SYN_TYP = ' ';
    public LNCSPART_PART_INF[] PART_INF = new LNCSPART_PART_INF[10];
    public LNCSPART_DATA() {
        for (int i=0;i<10;i++) PART_INF[i] = new LNCSPART_PART_INF();
    }
}
