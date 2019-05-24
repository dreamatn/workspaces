package com.hisun.BP;

public class BPCXP28 {
    public char FUNC = ' ';
    public String TYPE = " ";
    public String CODE = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public char FLAG = ' ';
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public short VAL_LEN = 0;
    public String TR_MMO = " ";
    public char STUS = ' ';
    public char SYS_LVL = ' ';
    public char RUN_MODE = ' ';
    public char LOG_IND = ' ';
    public char REEN_IND = ' ';
    public short CLS = 0;
    public char SELF_GRNT = ' ';
    public String AUTH_LVL = " ";
    public char APVL_IND = ' ';
    public char OWN_SYS_OPT = ' ';
    public char HIS_REC_IND = ' ';
    public char FINT_IND = ' ';
    public char REVT_IND = ' ';
    public String SUBS_TX_CODE = " ";
    public char AWA_IND = ' ';
    public String INP_FMT = " ";
    public String OUTP_FMT = " ";
    public String OUTP_CNTL = " ";
    public String SPEC_CNTL = " ";
    public String WAIT_BSP1 = " ";
    public String WAIT_BSP2 = " ";
    public String WAIT_BSP3 = " ";
    public int PGM_CNT = 0;
    public BPCXP28_FILE_TBL[] FILE_TBL = new BPCXP28_FILE_TBL[5];
    public BPCXP28() {
        for (int i=0;i<5;i++) FILE_TBL[i] = new BPCXP28_FILE_TBL();
    }
}
