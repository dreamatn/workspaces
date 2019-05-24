package com.hisun.TD;

import com.hisun.SM.SMRTRTT_PGM;

public class TRTT_DATA_TXT {
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
    SMRTRTT_PGM[] PGM = new SMRTRTT_PGM[5];
    TRTT_WAIT_BSP WAIT_BSP = new TRTT_WAIT_BSP();
    public TRTT_DATA_TXT() {
        for (int i=0;i<5;i++) PGM[i] = new SMRTRTT_PGM();
    }
}
