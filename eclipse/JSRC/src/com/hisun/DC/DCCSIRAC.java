package com.hisun.DC;

public class DCCSIRAC {
    public char FUNC = ' ';
    public DCCSIRAC_KEY KEY = new DCCSIRAC_KEY();
    public String PROD_NM = " ";
    public char CI_TYP = ' ';
    public DCCSIRAC_CCY_INF[] CCY_INF = new DCCSIRAC_CCY_INF[10];
    public char LN_MTH = ' ';
    public char LN_PER = ' ';
    public char DD_PER = ' ';
    public String TERM = " ";
    public double STRAMT = 0;
    public int STRDT = 0;
    public int EXPDT = 0;
    public DCCSIRAC() {
        for (int i=0;i<10;i++) CCY_INF[i] = new DCCSIRAC_CCY_INF();
    }
}