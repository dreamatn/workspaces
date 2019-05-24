package com.hisun.DC;

public class DCCIRPRD {
    public DCCIRPRD_KEY KEY = new DCCIRPRD_KEY();
    public String PROD_NM = " ";
    public char CI_TYP = ' ';
    public DCCIRPRD_CCY_INF[] CCY_INF = new DCCIRPRD_CCY_INF[10];
    public char LN_MTH = ' ';
    public char LN_PER = ' ';
    public char DD_PER = ' ';
    public String TERM = " ";
    public double STRAMT = 0;
    public int STRDT = 0;
    public int EXPDT = 0;
    public DCCIRPRD() {
        for (int i=0;i<10;i++) CCY_INF[i] = new DCCIRPRD_CCY_INF();
    }
}
