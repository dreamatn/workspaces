package com.hisun.BP;

public class BPCI4901 {
    public String TLR = " ";
    public String STAFNO = " ";
    public int TLR_BR = 0;
    public short TLR_TKS = 0;
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public String TLR_CNAM = " ";
    public String TLR_ENAM = " ";
    public String TLR_TYPE = " ";
    public char TLR_LVL = ' ';
    public char TX_LVL = ' ';
    public char AUTH_LVL = ' ';
    public char AUTH_REG = ' ';
    public char CBR_SIGN = ' ';
    public char TRM_TYPE = ' ';
    public char TX_MODE = ' ';
    public String PRT_IP = " ";
    public String TELE = " ";
    public String PST_ADDR = " ";
    public char SG_TRM_F = ' ';
    public BPCI4901_TRM_INFO[] TRM_INFO = new BPCI4901_TRM_INFO[10];
    public char PSW_TYP = ' ';
    public String DEV_ID = " ";
    public String TLR_STSW = " ";
    public char STW1 = ' ';
    public char STW2 = ' ';
    public char STW3 = ' ';
    public char STW4 = ' ';
    public char STW5 = ' ';
    public char STW6 = ' ';
    public char STW7 = ' ';
    public char STW8 = ' ';
    public char STW9 = ' ';
    public char STW10 = ' ';
    public char STW11 = ' ';
    public char STW12 = ' ';
    public int OUT_LMT = 0;
    public char SEN_CUS = ' ';
    public char SEN_GL = ' ';
    public char SUP_FLG = ' ';
    public String P_NAME1 = " ";
    public String P_NAME2 = " ";
    public String P_NAME3 = " ";
    public String CHNL_NO = " ";
    public BPCI4901() {
        for (int i=0;i<10;i++) TRM_INFO[i] = new BPCI4901_TRM_INFO();
    }
}
