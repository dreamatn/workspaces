package com.hisun.GD;

public class GDCSBPAC {
    public char MN_FLG = ' ';
    public String TX_CCY = " ";
    public char TX_CCYTYP = ' ';
    public int TX_BR = 0;
    public char TXSTLT = ' ';
    public double TXAMT = 0;
    public double PNAMT = 0;
    public String TX_CRAC = " ";
    public char ADV_FLG = ' ';
    public String ADV_AC = " ";
    public String CNTR_NO = " ";
    public String TXRMK = " ";
    public String TXMMO = " ";
    public int TX_CRBR = 0;
    public String TX_CRNO = " ";
    public String ADV_TYPE = " ";
    public String CI_NO = " ";
    public String PROD_TYP = " ";
    public short OCAL_PD = 0;
    public char OCAL_UT = ' ';
    public char DW_BK_TP = ' ';
    public String DRAW_ACT = " ";
    public String DRAW_AC = " ";
    public char PA_BK_TP = ' ';
    public String PAY_AC_T = " ";
    public String PAY_AC = " ";
    public char PEN_RRAT = ' ';
    public char PEN_TYP = ' ';
    public String PEN_RATT = " ";
    public String PEN_RATC = " ";
    public double PEN_SPR = 0;
    public double PEN_PCT = 0;
    public double PEN_IRAT = 0;
    public char CPND_USE = ' ';
    public char INT_MTH = ' ';
    public char CPND_RTY = ' ';
    public char CPND_TYP = ' ';
    public String CPNDRATT = " ";
    public String CPNDRATC = " ";
    public double CPND_SPR = 0;
    public double CPND_PCT = 0;
    public double CPNDIRAT = 0;
    public String CNTRT_NO = " ";
    public double DRW_AMT = 0;
    public GDCSBPAC_DR_SEC[] DR_SEC = new GDCSBPAC_DR_SEC[5];
    public int REQ_DATE = 0;
    public int TR_BR = 0;
    public GDCSBPAC() {
        for (int i=0;i<5;i++) DR_SEC[i] = new GDCSBPAC_DR_SEC();
    }
}
