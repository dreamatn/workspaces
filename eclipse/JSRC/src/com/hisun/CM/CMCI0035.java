package com.hisun.CM;

public class CMCI0035 {
    public char TR_TYPE = ' ';
    public String REQ_JRN = " ";
    public int REQ_DATE = 0;
    public String REQ_SYS = " ";
    public String REQ_CHNL = " ";
    public String TL_NO = " ";
    public int TR_BR = 0;
    public String AP_REF = " ";
    public String CHN_TYP = " ";
    public CMCI0035_AC_DATA[] AC_DATA = new CMCI0035_AC_DATA[2];
    public CMCI0035_MIB_DATA[] MIB_DATA = new CMCI0035_MIB_DATA[5];
    public String F_PRO_CD = " ";
    public String FIN_AC = " ";
    public String HLD_NO = " ";
    public char HLD_TYP = ' ';
    public double HLD_AMT = 0;
    public int VAL_DATE = 0;
    public int EXP_DATE = 0;
    public String HLD_RSN = " ";
    public String SMR = " ";
    public char PROC_STS = ' ';
    public String RET_CODE = " ";
    public String RET_MSG = " ";
    public int DATE = 0;
    public long JRNNO = 0;
    public String VCH_NO = " ";
    public CMCI0035_R_AC_DAT[] R_AC_DAT = new CMCI0035_R_AC_DAT[2];
    public CMCI0035() {
        for (int i=0;i<2;i++) AC_DATA[i] = new CMCI0035_AC_DATA();
        for (int i=0;i<5;i++) MIB_DATA[i] = new CMCI0035_MIB_DATA();
        for (int i=0;i<2;i++) R_AC_DAT[i] = new CMCI0035_R_AC_DAT();
    }
}
