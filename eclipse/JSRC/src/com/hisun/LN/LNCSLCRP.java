package com.hisun.LN;

public class LNCSLCRP {
    public LNCSLCRP_RC RC = new LNCSLCRP_RC();
    public String DRAW_NO = " ";
    public String CONT_NO = " ";
    public String PRODMO = " ";
    public String PROD_TYP = " ";
    public double CONT_AMT = 0;
    public int BOOK_BR = 0;
    public String CCY = " ";
    public int START_DT = 0;
    public char ATO_DFLG = ' ';
    public char SYN_TYP = ' ';
    public LNCSLCRP_PART_INF[] PART_INF = new LNCSLCRP_PART_INF[10];
    public LNCSLCRP() {
        for (int i=0;i<10;i++) PART_INF[i] = new LNCSLCRP_PART_INF();
    }
}
