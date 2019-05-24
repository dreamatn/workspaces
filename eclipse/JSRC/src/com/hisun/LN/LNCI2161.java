package com.hisun.LN;

public class LNCI2161 {
    public String PAPER_NO = " ";
    public String DRAW_NO = " ";
    public String CONT_NO = " ";
    public int BOOK_CRE = 0;
    public int DOMI_BR = 0;
    public String CI_NO = " ";
    public String PROD_TYP = " ";
    public String VCH_FLG = " ";
    public int START_DT = 0;
    public int MATU_DT = 0;
    public String CCY = " ";
    public double CONT_AMT = 0;
    public char INT_RATT = ' ';
    public double INT_IRA = 0;
    public char PEN_RATT = ' ';
    public double PEN_IRA = 0;
    public char CPND_USE = ' ';
    public char CPNDRATT = ' ';
    public double CPND_IRA = 0;
    public char ABUSRATT = ' ';
    public double ABUS_IRA = 0;
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
    public char SP_LNFLG = ' ';
    public char PHS_FLG = ' ';
    public short PHS_NUM = 0;
    public LNCI2161_PHS_INFO[] PHS_INFO = new LNCI2161_PHS_INFO[5];
    public char OLC_PERU = ' ';
    public short OLC_PERD = 0;
    public String DRAW_ACT = " ";
    public String DRAW_AC = " ";
    public char DW_BK_TP = ' ';
    public String DRAW_SEQ = " ";
    public String PAY_AC_T = " ";
    public String PAY_AC = " ";
    public char PA_BK_TP = ' ';
    public char FS_CLMTH = ' ';
    public double FS_CLAMT = 0;
    public String PAYI_ACT = " ";
    public String PAYI_AC = " ";
    public String PAYI_SEQ = " ";
    public char YHS_FLG = ' ';
    public String YHS_AC = " ";
    public char ATO_DFLG = ' ';
    public int ATO_DSEQ = 0;
    public double ATO_AMT = 0;
    public String ATO_ACT = " ";
    public String ATO_AC = " ";
    public String PRO_AC = " ";
    public double PRO_AMT = 0;
    public char GRA_TYP = ' ';
    public int GRA_DAYA = 0;
    public char P_GRA_MT = ' ';
    public char C_GRA_MT = ' ';
    public String INT_D_BA = " ";
    public String PSEQ_CD = " ";
    public double PREP_CHR = 0;
    public double HAND_CHR = 0;
    public char HAND_CHM = ' ';
    public String GUAPREF = " ";
    public char GUARDUAP = ' ';
    public char GUARPSEQ = ' ';
    public String FUND_ACC = " ";
    public String SETTLE_A = " ";
    public String SETTLE_B = " ";
    public int G_PRJ_NO = 0;
    public int PROJ_NO = 0;
    public short SUBSTTRM = 0;
    public short SUBSBTRM = 0;
    public int B_PRJ_NO = 0;
    public double BX_AMT = 0;
    public LNCI2161_FEE_INFO[] FEE_INFO = new LNCI2161_FEE_INFO[5];
    public String FEE_ACT = " ";
    public String FEE_AC = " ";
    public String TR_MMO = " ";
    public String REMARK1 = " ";
    public String CUSTM1 = " ";
    public String CUSTM2 = " ";
    public String CUSTM3 = " ";
    public String BAR_CD = " ";
    public String UNIT_CD = " ";
    public LNCI2161() {
        for (int i=0;i<5;i++) PHS_INFO[i] = new LNCI2161_PHS_INFO();
        for (int i=0;i<5;i++) FEE_INFO[i] = new LNCI2161_FEE_INFO();
    }
}