package com.hisun.TD;

public class TRTP_DATA_TXT {
    String TR_MMO = " ";
    char STUS = ' ';
    char SYS_LVL = ' ';
    char RUN_MODE = ' ';
    char LOG_IND = ' ';
    char REEN_IND = ' ';
    short CLS = 0;
    char SELF_GRNT = ' ';
    String AUTH_LVL = " ";
    char APVL_IND = ' ';
    char OWN_SYS_IND = ' ';
    char HIS_REC_IND = ' ';
    String ATTR_WORD = " ";
    String SUBS_TX_CODE = " ";
    char AWA_IND = ' ';
    String INP_FMT = " ";
    String OUTP_FMT = " ";
    String OUTP_CNTL = " ";
    String SPEC_CNTL = " ";
    int PGM_CNT = 0;
    TRTP_PGM[] PGM = new TRTP_PGM[5];
    TRTP_WAIT_BSP WAIT_BSP = new TRTP_WAIT_BSP();
    public TRTP_DATA_TXT() {
        for (int i=0;i<5;i++) PGM[i] = new TRTP_PGM();
    }
}
