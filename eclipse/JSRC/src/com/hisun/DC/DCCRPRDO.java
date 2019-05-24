package com.hisun.DC;

public class DCCRPRDO {
    public char FUNC = ' ';
    public String PROD_CODE = " ";
    public String PROD_NM = " ";
    public char CI_TYP = ' ';
    public DCCRPRDO_CCY_INF[] CCY_INF = new DCCRPRDO_CCY_INF[10];
    public char LN_MTH = ' ';
    public char LN_PER = ' ';
    public char DD_PER = ' ';
    public String TERM = " ";
    public double STRAMT = 0;
    public int STRDT = 0;
    public int EXPDT = 0;
    public DCCRPRDO() {
        for (int i=0;i<10;i++) CCY_INF[i] = new DCCRPRDO_CCY_INF();
    }
}
