package com.hisun.TD;

public class TDCPOOD_OTH_PRM {
    public char SPEC_PRD_TYP = ' ';
    public short MAX_NUM = 0;
    public char INT_RUL = ' ';
    public int PAY_GRACE = 0;
    public String PAY_PERD = " ";
    public String MIN_TERM = " ";
    public String MAX_TERM = " ";
    public char COMPINT_FLG = ' ';
    public char INTPLAN_FLG = ' ';
    public char PENALTY_FLG = ' ';
    public int UNINT_DAYS = 0;
    public String INT_PERD = " ";
    public int MAX_GRACE = 0;
    public char CCY_TYP = ' ';
    public TDCPOOD_CCY_INF[] CCY_INF = new TDCPOOD_CCY_INF[20];
    public char MID_FLG = ' ';
    public char DELA_FLG = ' ';
    public char DOCU_SET_FLG = ' ';
    public char CPRAT_TYP = ' ';
    public char FRZ_FLG = ' ';
    public TDCPOOD_OTH_PRM() {
        for (int i=0;i<20;i++) CCY_INF[i] = new TDCPOOD_CCY_INF();
    }
}
