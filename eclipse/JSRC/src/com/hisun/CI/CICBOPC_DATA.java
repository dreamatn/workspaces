package com.hisun.CI;

public class CICBOPC_DATA {
    public String CI_NM = " ";
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
    public double PER_INC = 0;
    public char ORGIN_TP = ' ';
    public String ORGIN1 = " ";
    public String ORGIN2 = " ";
    public CICBOPC_ADR_DATA[] ADR_DATA = new CICBOPC_ADR_DATA[2];
    public CICBOPC_CNT_DATA[] CNT_DATA = new CICBOPC_CNT_DATA[2];
    public String TAX_BANK = " ";
    public String TAX_ACNO = " ";
    public char TAX_TYPE = ' ';
    public String TAX_DSNO = " ";
    public String CRS_TYPE = " ";
    public String CRS_DESC = " ";
    public int PROOF_DT = 0;
    public char PROOF_CH = ' ';
    public CICBOPC_CRS_DATA[] CRS_DATA = new CICBOPC_CRS_DATA[25];
    public CICBOPC_DATA() {
        for (int i=0;i<2;i++) ADR_DATA[i] = new CICBOPC_ADR_DATA();
        for (int i=0;i<2;i++) CNT_DATA[i] = new CICBOPC_CNT_DATA();
        for (int i=0;i<25;i++) CRS_DATA[i] = new CICBOPC_CRS_DATA();
    }
}
