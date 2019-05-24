package com.hisun.DC;

public class DCCSIRLN_DATA {
    public DCCSIRLN_KEY KEY = new DCCSIRLN_KEY();
    public String PROD_NM = " ";
    public char CI_TYP = ' ';
    public DCCSIRLN_CCY_INF[] CCY_INF = new DCCSIRLN_CCY_INF[10];
    public char LN_MTH = ' ';
    public char LN_PER = ' ';
    public char DD_PER = ' ';
    public String TERM = " ";
    public double STRAMT = 0;
    public int STRDT = 0;
    public int EXPDT = 0;
    public DCCSIRLN_DATA() {
        for (int i=0;i<10;i++) CCY_INF[i] = new DCCSIRLN_CCY_INF();
    }
}
