package com.hisun.TD;

public class TDC1340 {
    public TDC1340_AC_INFO[] AC_INFO = new TDC1340_AC_INFO[200];
    public char BV_TYP = ' ';
    public String BV_CD = " ";
    public String NAME = " ";
    public char FILLER7 = 0X02;
    public String PRDAC_CD = " ";
    public double TXN_AMT = 0;
    public char FILLER10 = 0X02;
    public String CCY = " ";
    public int OPEN_DATE = 0;
    public int TX_DATE = 0;
    public int VAL_DATE = 0;
    public int EXP_DATE = 0;
    public String TERM = " ";
    public double INT_RAT = 0;
    public char FILLER18 = 0X02;
    public double EXP_INT = 0;
    public char DRAW_MTH = ' ';
    public int BR = 0;
    public String FRG_IND = " ";
    public String AC_STSW = " ";
    public TDC1340() {
        for (int i=0;i<200;i++) AC_INFO[i] = new TDC1340_AC_INFO();
    }
}
