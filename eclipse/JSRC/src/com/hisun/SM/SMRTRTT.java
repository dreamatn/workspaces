package com.hisun.SM;

public class SMRTRTT {
    public SMRTRTT_KEY KEY = new SMRTRTT_KEY();
    public String TR_MMO = " ";
    public char STUS = ' ';
    public char SYS_LVL = ' ';
    public char RUN_MODE = ' ';
    public char LOG_IND = ' ';
    public char REEN_IND = ' ';
    public short CLS = 0;
    public char SELF_GRNT = ' ';
    public short AUTH_LVL = 0;
    public char APVL_IND = ' ';
    public char ODM_PRN_IND = ' ';
    public char HIS_REC_IND = ' ';
    public String ATTR_WORD = " ";
    public short SUBS_AP_CODE = 0;
    public short SUBS_TR_CODE = 0;
    public char AWA_IND = ' ';
    public short INP_FMT = 0;
    public short OUTP_FMT = 0;
    public String OUTP_CNTL = " ";
    public String SPEC_CNTL = " ";
    public short PGM_CNT = 0;
    public SMRTRTT_PGM[] PGM = new SMRTRTT_PGM[5];
    public SMRTRTT() {
        for (int i=0;i<5;i++) PGM[i] = new SMRTRTT_PGM();
    }
}
