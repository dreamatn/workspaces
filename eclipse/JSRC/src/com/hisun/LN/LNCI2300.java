package com.hisun.LN;

public class LNCI2300 {
    public String PAPER_NO = " ";
    public String DRAW_NO = " ";
    public String CONT_NO = " ";
    public int BOOK_CRE = 0;
    public int DOMI_BR = 0;
    public String CI_NO = " ";
    public String PROD_TYP = " ";
    public int START_DT = 0;
    public int MATU_DT = 0;
    public char CCY_TYP = ' ';
    public String CCY = " ";
    public double CONT_AMT = 0;
    public char REPY_MTH = ' ';
    public char INST_MTH = ' ';
    public char PAYI_PER = ' ';
    public char CAL_PERU = ' ';
    public short CAL_PERD = 0;
    public char PAY_DTYP = ' ';
    public short CAL_DAY = 0;
    public int CAL_FSDT = 0;
    public char PYP_CIRC = ' ';
    public char PYP_PERU = ' ';
    public short PYP_PERD = 0;
    public char DW_BK_TP = ' ';
    public String DRAW_ACT = " ";
    public String DRAW_AC = " ";
    public String DRAW_SEQ = " ";
    public char PA_BK_TP = ' ';
    public String PAY_AC_T = " ";
    public String PAY_AC = " ";
    public int GJPRJNO = 0;
    public String GUAR_AC = " ";
    public double GUAR_AMT = 0;
    public String KFS_AC = " ";
    public String INT_D_BA = " ";
    public char IRAT_TP1 = ' ';
    public double IN_RATE = 0;
    public char PEN_RATT = ' ';
    public double PEN_IRAT = 0;
    public char CPND_USE = ' ';
    public char CPNDRATT = ' ';
    public double CPND_IRA = 0;
    public char ABUSRATT = ' ';
    public double ABUSIRAT = 0;
    public String GUAPREF = " ";
    public char GUARDUAP = ' ';
    public char GUARPSEQ = ' ';
    public char OLC_PERU = ' ';
    public short OLC_PERD = 0;
    public String TR_MMO = " ";
    public String REMARK1 = " ";
    public LNCI2300_FEE_INF[] FEE_INF = new LNCI2300_FEE_INF[5];
    public String FEE_ACT = " ";
    public String FEE_AC = " ";
    public double YHS_AMT = 0;
    public String CUSTM1 = " ";
    public String CUSTM2 = " ";
    public String CUSTM3 = " ";
    public String BAR_CD = " ";
    public String UNIT_CD = " ";
    public LNCI2300() {
        for (int i=0;i<5;i++) FEE_INF[i] = new LNCI2300_FEE_INF();
    }
}