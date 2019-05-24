package com.hisun.BP;

public class BPCPQTRT {
    public BPCPQTRT_RC RC = new BPCPQTRT_RC();
    public String CD = " ";
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public String DESC = " ";
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
    public char ODM_PRN_IND = ' ';
    public char HIS_REC_IND = ' ';
    public String ATTR_WORD = " ";
    public String SUBS_TX_CODE = " ";
    public char AWA_IND = ' ';
    public String INP_FMT = " ";
    public String OUTP_FMT = " ";
    public String OUTP_CNTL = " ";
    public String SPEC_CNTL = " ";
    public int PGM_CNT = 0;
    public BPCPQTRT_PGM[] PGM = new BPCPQTRT_PGM[5];
    public BPCPQTRT() {
        for (int i=0;i<5;i++) PGM[i] = new BPCPQTRT_PGM();
    }
}
