package com.hisun.BP;

public class BPRTRT_DATA_TXT {
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
    public char OWN_SYS_IND = ' ';
    public char HIS_REC_IND = ' ';
    public String ATTR_WORD = " ";
    public String SUBS_TX_CODE = " ";
    public char AWA_IND = ' ';
    public String INP_FMT = " ";
    public String OUTP_FMT = " ";
    public String OUTP_CNTL = " ";
    public String SPEC_CNTL = " ";
    public int PGM_CNT = 0;
    public BPRTRT_PGM[] PGM = new BPRTRT_PGM[5];
    public BPRTRT_WAIT_BSP WAIT_BSP = new BPRTRT_WAIT_BSP();
    public BPRTRT_DATA_TXT() {
        for (int i=0;i<5;i++) PGM[i] = new BPRTRT_PGM();
    }
}
