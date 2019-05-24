package com.hisun.DC;

public class DCCI0204 {
    public char FUNC = ' ';
    public String PROD_CD = " ";
    public String PROD_NM = " ";
    public char CI_TYP = ' ';
    public DCCI0204_CCY_INF[] CCY_INF = new DCCI0204_CCY_INF[10];
    public char LN_MTH = ' ';
    public char LN_PER = ' ';
    public char DD_PER = ' ';
    public String TERM = " ";
    public double STRAMT = 0;
    public int STRDT = 0;
    public int EXPDT = 0;
    public DCCI0204() {
        for (int i=0;i<10;i++) CCY_INF[i] = new DCCI0204_CCY_INF();
    }
}
