package com.hisun.LN;

public class LNB5000_AWA_5000 {
    public char FUN_CD = ' ';
    public short FUN_CD_NO = 0;
    public String LN_CTANO = " ";
    public short LN_CTANO_NO = 0;
    public String LN_AC = " ";
    public short LN_AC_NO = 0;
    public String PAPER_NM = " ";
    public short PAPER_NM_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public int MAIN_BR = 0;
    public short MAIN_BR_NO = 0;
    public double AMT = 0;
    public short AMT_NO = 0;
    public char SYN_TYP = ' ';
    public short SYN_TYP_NO = 0;
    public LNB5000_PART_INF[] PART_INF = new LNB5000_PART_INF[10];
    public LNB5000_AWA_5000() {
        for (int i=0;i<10;i++) PART_INF[i] = new LNB5000_PART_INF();
    }
}
