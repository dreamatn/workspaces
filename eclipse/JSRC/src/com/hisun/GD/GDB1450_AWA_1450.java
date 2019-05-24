package com.hisun.GD;

public class GDB1450_AWA_1450 {
    public char TR_TYPE = ' ';
    public short TR_TYPE_NO = 0;
    public String REQ_JRN = " ";
    public short REQ_JRN_NO = 0;
    public int REQ_DATE = 0;
    public short REQ_DATE_NO = 0;
    public String REQ_SYS = " ";
    public short REQ_SYS_NO = 0;
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public String AP_REF = " ";
    public short AP_REF_NO = 0;
    public GDB1450_DR_SEC[] DR_SEC = new GDB1450_DR_SEC[5];
    public char MN_FLG = ' ';
    public short MN_FLG_NO = 0;
    public String TXCCY = " ";
    public short TXCCY_NO = 0;
    public char TXTYP = ' ';
    public short TXTYP_NO = 0;
    public int TXBR = 0;
    public short TXBR_NO = 0;
    public double PN_AMT = 0;
    public short PN_AMT_NO = 0;
    public double TXAMT_S = 0;
    public short TXAMT_S_NO = 0;
    public int TX_CRBR = 0;
    public short TX_CRBR_NO = 0;
    public String TX_CRNO = " ";
    public short TX_CRNO_NO = 0;
    public char TXADV_F = ' ';
    public short TXADV_F_NO = 0;
    public int TXADV_AC = 0;
    public short TXADV_AC_NO = 0;
    public String CNTR_NO = " ";
    public short CNTR_NO_NO = 0;
    public String TXRMK = " ";
    public short TXRMK_NO = 0;
    public String TXMMO = " ";
    public short TXMMO_NO = 0;
    public char PROC_STS = ' ';
    public short PROC_STS_NO = 0;
    public String RET_CODE = " ";
    public short RET_CODE_NO = 0;
    public String RET_MSG = " ";
    public short RET_MSG_NO = 0;
    public int DATE = 0;
    public short DATE_NO = 0;
    public long JRNNO = 0;
    public short JRNNO_NO = 0;
    public double TX_AMT1 = 0;
    public short TX_AMT1_NO = 0;
    public double TX_AMT2 = 0;
    public short TX_AMT2_NO = 0;
    public double TX_AMT3 = 0;
    public short TX_AMT3_NO = 0;
    public double TX_AMT4 = 0;
    public short TX_AMT4_NO = 0;
    public double TX_AMT5 = 0;
    public short TX_AMT5_NO = 0;
    public double ADV_AMT = 0;
    public short ADV_AMT_NO = 0;
    public String ADV_TYPE = " ";
    public short ADV_TYPE_NO = 0;
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public String PROD_TYP = " ";
    public short PROD_TYP_NO = 0;
    public short OCAL_PD = 0;
    public short OCAL_PD_NO = 0;
    public char OCAL_UT = ' ';
    public short OCAL_UT_NO = 0;
    public char DW_BK_TP = ' ';
    public short DW_BK_TP_NO = 0;
    public String DRAW_ACT = " ";
    public short DRAW_ACT_NO = 0;
    public String DRAW_AC = " ";
    public short DRAW_AC_NO = 0;
    public char PA_BK_TP = ' ';
    public short PA_BK_TP_NO = 0;
    public String PAY_AC_T = " ";
    public short PAY_AC_T_NO = 0;
    public String PAY_AC = " ";
    public short PAY_AC_NO = 0;
    public char PEN_RRAT = ' ';
    public short PEN_RRAT_NO = 0;
    public char PEN_TYP = ' ';
    public short PEN_TYP_NO = 0;
    public String PEN_RATT = " ";
    public short PEN_RATT_NO = 0;
    public String PEN_RATC = " ";
    public short PEN_RATC_NO = 0;
    public double PEN_SPR = 0;
    public short PEN_SPR_NO = 0;
    public double PEN_PCT = 0;
    public short PEN_PCT_NO = 0;
    public double PEN_IRAT = 0;
    public short PEN_IRAT_NO = 0;
    public char CPND_USE = ' ';
    public short CPND_USE_NO = 0;
    public char INT_MTH = ' ';
    public short INT_MTH_NO = 0;
    public char CPND_RTY = ' ';
    public short CPND_RTY_NO = 0;
    public char CPND_TYP = ' ';
    public short CPND_TYP_NO = 0;
    public String CPNDRATT = " ";
    public short CPNDRATT_NO = 0;
    public String CPNDRATC = " ";
    public short CPNDRATC_NO = 0;
    public double CPND_SPR = 0;
    public short CPND_SPR_NO = 0;
    public double CPND_PCT = 0;
    public short CPND_PCT_NO = 0;
    public double CPNDIRAT = 0;
    public short CPNDIRAT_NO = 0;
    public String CNTRT_NO = " ";
    public short CNTRT_NO_NO = 0;
    public GDB1450_AWA_1450() {
        for (int i=0;i<5;i++) DR_SEC[i] = new GDB1450_DR_SEC();
    }
}