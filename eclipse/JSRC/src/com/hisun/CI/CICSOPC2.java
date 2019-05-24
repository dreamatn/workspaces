package com.hisun.CI;

public class CICSOPC2 {
    public String CI_NM = " ";
    public String CI_FNM = " ";
    public String CI_SNM = " ";
    public String ID_TYPE = " ";
    public String ID_NO = " ";
    public int ID_EXPDT = 0;
    public String ID_RMK = " ";
    public String SUB_TYP = " ";
    public char RESIDENT = ' ';
    public char SEX = ' ';
    public int BIRTH_DT = 0;
    public String REG_CNTY = " ";
    public String NATION = " ";
    public String OCCUP = " ";
    public char VER_FLG = ' ';
    public char SID_FLG = ' ';
    public char EMP_FLG = ' ';
    public char PL_FLG = ' ';
    public String CORP_NM = " ";
    public double PER_INC = 0;
    public char ORGIN_TP = ' ';
    public String ORGIN1 = " ";
    public String ORGIN2 = " ";
    public int OPEN_BR = 0;
    public String REQ_SYS = " ";
    public char BK_EMP = ' ';
    public char CDG_EMP = ' ';
    public String HKC_TYP = " ";
    public CICSOPC2_ADR_DATA[] ADR_DATA = new CICSOPC2_ADR_DATA[2];
    public CICSOPC2_CNT_DATA[] CNT_DATA = new CICSOPC2_CNT_DATA[2];
    public String TAX_BANK = " ";
    public String TAX_ACNO = " ";
    public char TAX_TYPE = ' ';
    public String TAX_DSNO = " ";
    public String FTA_FLG = " ";
    public int W8W9_DT = 0;
    public String TIN_NO = " ";
    public String GIIN_CD = " ";
    public String CRS_TYPE = " ";
    public String CRS_DESC = " ";
    public int PROOF_DT = 0;
    public char PROOF_CH = ' ';
    public CICSOPC2_CRS_DATA[] CRS_DATA = new CICSOPC2_CRS_DATA[25];
    public CICSOPC2() {
        for (int i=0;i<2;i++) ADR_DATA[i] = new CICSOPC2_ADR_DATA();
        for (int i=0;i<2;i++) CNT_DATA[i] = new CICSOPC2_CNT_DATA();
        for (int i=0;i<25;i++) CRS_DATA[i] = new CICSOPC2_CRS_DATA();
    }
}
