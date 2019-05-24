package com.hisun.DC;

public class DCB0204_AWA_0204 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String PROD_CD = " ";
    public short PROD_CD_NO = 0;
    public String PROD_NM = " ";
    public short PROD_NM_NO = 0;
    public char CI_TYP = ' ';
    public short CI_TYP_NO = 0;
    public DCB0204_CCY_INF[] CCY_INF = new DCB0204_CCY_INF[10];
    public char LN_MTH = ' ';
    public short LN_MTH_NO = 0;
    public char LN_PER = ' ';
    public short LN_PER_NO = 0;
    public char DD_PER = ' ';
    public short DD_PER_NO = 0;
    public String TERM = " ";
    public short TERM_NO = 0;
    public double STRAMT = 0;
    public short STRAMT_NO = 0;
    public int STRDT = 0;
    public short STRDT_NO = 0;
    public int EXPDT = 0;
    public short EXPDT_NO = 0;
    public DCB0204_AWA_0204() {
        for (int i=0;i<10;i++) CCY_INF[i] = new DCB0204_CCY_INF();
    }
}
