package com.hisun.CI;

public class CICOSQPC_DATA {
    public String CI_NO = " ";
    public char CI_TYP = ' ';
    public char CI_ATTR = ' ';
    public String STSW = " ";
    public String IDE_STSW = " ";
    public String CI_NM = " ";
    public char FILLER8 = 0X02;
    public String CI_ENM = " ";
    public char FILLER10 = 0X02;
    public String CI_FNM = " ";
    public char FILLER12 = 0X02;
    public String CI_SNM = " ";
    public char FILLER14 = 0X02;
    public String CI_OFNM = " ";
    public char FILLER16 = 0X02;
    public String CI_OSNM = " ";
    public char FILLER18 = 0X02;
    public String CI_ONM = " ";
    public char FILLER20 = 0X02;
    public String ID_TYPE = " ";
    public String ID_NO = " ";
    public char FILLER23 = 0X02;
    public String ID_RMK = " ";
    public char FILLER25 = 0X02;
    public String SUB_TYP = " ";
    public char RESIDENT = ' ';
    public char SEX = ' ';
    public int BIRTH_DT = 0;
    public String REG_CNTY = " ";
    public String NATION = " ";
    public String OCCUP = " ";
    public String MARI = " ";
    public char VER_FLG = ' ';
    public char SID_FLG = ' ';
    public String OIC_NO = " ";
    public String BIRTH_RG = " ";
    public String EDU_LVL = " ";
    public double PER_INC = 0;
    public double FAM_INC = 0;
    public String CORP_NM = " ";
    public char FILLER42 = 0X02;
    public String CORP_TYP = " ";
    public String CORP_IND = " ";
    public char WK_TITL = ' ';
    public String REMARK = " ";
    public char FILLER47 = 0X02;
    public String NRA_TAX = " ";
    public char ORGIN_TP = ' ';
    public String ORGIN1 = " ";
    public String ORGIN2 = " ";
    public char FILLER52 = 0X02;
    public String RESP_CD = " ";
    public String SUB_DP = " ";
    public String TAX_BANK = " ";
    public char FILLER56 = 0X02;
    public String TAX_ACNO = " ";
    public char TAX_TYPE = ' ';
    public String TAX_DSNO = " ";
    public String FATCA_TP = " ";
    public int W8W9_DT = 0;
    public String TIN_NO = " ";
    public String GIIN_CD = " ";
    public String CRS_TYPE = " ";
    public String CRS_DESC = " ";
    public char FILLER66 = 0X02;
    public int PROOF_DT = 0;
    public char PROOF_CH = ' ';
    public String HKC_TYP = " ";
    public CICOSQPC_CRS_AREA[] CRS_AREA = new CICOSQPC_CRS_AREA[25];
    public CICOSQPC_DATA() {
        for (int i=0;i<25;i++) CRS_AREA[i] = new CICOSQPC_CRS_AREA();
    }
}