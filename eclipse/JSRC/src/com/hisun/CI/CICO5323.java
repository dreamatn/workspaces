package com.hisun.CI;

public class CICO5323 {
    public String CI_NO = " ";
    public char CI_TYP = ' ';
    public char CI_ATTR = ' ';
    public String NM_TYP1 = " ";
    public String CI_NM1 = " ";
    public char FILLER6 = 0X02;
    public String NM_TYP2 = " ";
    public String CI_NM2 = " ";
    public char FILLER9 = 0X02;
    public String INT_TYP = " ";
    public String OPID_TYP = " ";
    public String OPID_NO = " ";
    public char FILLER13 = 0X02;
    public String OPREMARK = " ";
    public char FILLER15 = 0X02;
    public String OPID_RGN = " ";
    public int OPID_EXP = 0;
    public CICO5323_ID_GS[] ID_GS = new CICO5323_ID_GS[9];
    public char RS_TYP = ' ';
    public char BLK_TYP = ' ';
    public String SUB_TYP = " ";
    public char RES_FLG = ' ';
    public String CORG = " ";
    public String ECO = " ";
    public String HECO = " ";
    public String NED_TYP = " ";
    public int REG_DT = 0;
    public String REG_CCY = " ";
    public double REG_AMT = 0;
    public String BUSN_SCP = " ";
    public char FILLER39 = 0X02;
    public String INDUS = " ";
    public String ENC = " ";
    public char SID_FLG = ' ';
    public String HCNTY = " ";
    public char CASFLG = ' ';
    public String FCATYP = " ";
    public char SIZE = ' ';
    public char INSIZE = ' ';
    public CICO5323_ADR_GS[] ADR_GS = new CICO5323_ADR_GS[6];
    public CICO5323_CNT_GS[] CNT_GS = new CICO5323_CNT_GS[6];
    public CICO5323_REL_GS[] REL_GS = new CICO5323_REL_GS[3];
    public CICO5323() {
        for (int i=0;i<9;i++) ID_GS[i] = new CICO5323_ID_GS();
        for (int i=0;i<6;i++) ADR_GS[i] = new CICO5323_ADR_GS();
        for (int i=0;i<6;i++) CNT_GS[i] = new CICO5323_CNT_GS();
        for (int i=0;i<3;i++) REL_GS[i] = new CICO5323_REL_GS();
    }
}
