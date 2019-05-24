package com.hisun.CM;

public class CMB0035_AWA_0035 {
    public char TR_TYPE = ' ';
    public short TR_TYPE_NO = 0;
    public String REQ_JRN = " ";
    public short REQ_JRN_NO = 0;
    public int REQ_DATE = 0;
    public short REQ_DATE_NO = 0;
    public String REQ_SYS = " ";
    public short REQ_SYS_NO = 0;
    public String REQ_CHNL = " ";
    public short REQ_CHNL_NO = 0;
    public String TL_NO = " ";
    public short TL_NO_NO = 0;
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public String AP_REF = " ";
    public short AP_REF_NO = 0;
    public String CHN_TYP = " ";
    public short CHN_TYP_NO = 0;
    public CMB0035_AC_DATA[] AC_DATA = new CMB0035_AC_DATA[2];
    public CMB0035_MIB_DATA[] MIB_DATA = new CMB0035_MIB_DATA[5];
    public String F_PRO_CD = " ";
    public short F_PRO_CD_NO = 0;
    public String FIN_AC = " ";
    public short FIN_AC_NO = 0;
    public String HLD_NO = " ";
    public short HLD_NO_NO = 0;
    public char HLD_TYP = ' ';
    public short HLD_TYP_NO = 0;
    public double HLD_AMT = 0;
    public short HLD_AMT_NO = 0;
    public int VAL_DATE = 0;
    public short VAL_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public String HLD_RSN = " ";
    public short HLD_RSN_NO = 0;
    public String SMR = " ";
    public short SMR_NO = 0;
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
    public String VCH_NO = " ";
    public short VCH_NO_NO = 0;
    public CMB0035_R_AC_DAT[] R_AC_DAT = new CMB0035_R_AC_DAT[2];
    public CMB0035_AWA_0035() {
        for (int i=0;i<2;i++) AC_DATA[i] = new CMB0035_AC_DATA();
        for (int i=0;i<5;i++) MIB_DATA[i] = new CMB0035_MIB_DATA();
        for (int i=0;i<2;i++) R_AC_DAT[i] = new CMB0035_R_AC_DAT();
    }
}