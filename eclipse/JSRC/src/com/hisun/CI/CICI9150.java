package com.hisun.CI;

public class CICI9150 {
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
    public char PL_FLG = ' ';
    public double PER_INC = 0;
    public char ORGIN_TP = ' ';
    public String ORGIN1 = " ";
    public String ORGIN2 = " ";
    public int OPEN_BR = 0;
    public CICI9150_ADR_AREA[] ADR_AREA = new CICI9150_ADR_AREA[2];
    public CICI9150_CNT_AREA[] CNT_AREA = new CICI9150_CNT_AREA[2];
    public String TAX_BANK = " ";
    public String TAX_ACNO = " ";
    public char TAX_TYPE = ' ';
    public String TAX_DSNO = " ";
    public String CRS_TYPE = " ";
    public String CRS_DESC = " ";
    public int PROOF_DT = 0;
    public char PROOF_CH = ' ';
    public CICI9150_CRS_AREA[] CRS_AREA = new CICI9150_CRS_AREA[25];
    public CICI9150() {
        for (int i=0;i<2;i++) ADR_AREA[i] = new CICI9150_ADR_AREA();
        for (int i=0;i<2;i++) CNT_AREA[i] = new CICI9150_CNT_AREA();
        for (int i=0;i<25;i++) CRS_AREA[i] = new CICI9150_CRS_AREA();
    }
}
