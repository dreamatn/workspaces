package com.hisun.CI;

public class CICMSTC_DATA {
    public short STC_SEQ = 0;
    public char TYPE = ' ';
    public String PSW = " ";
    public char FIN_FLG = ' ';
    public String AC = " ";
    public String ADR_NM = " ";
    public String CI_NO = " ";
    public char PRT_PAPER = ' ';
    public char PRT_COND = ' ';
    public char PRT_PERM = ' ';
    public int PRT_DAY = 0;
    public int LAST_PRT_DT = 0;
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public char SEND_TYP = ' ';
    public String ADRS = " ";
    public CICMSTC_ADR_SEQS[] ADR_SEQS = new CICMSTC_ADR_SEQS[5];
    public CICMSTC_CNT_SEQS[] CNT_SEQS = new CICMSTC_CNT_SEQS[5];
    public CICMSTC_DATA() {
        for (int i=0;i<5;i++) ADR_SEQS[i] = new CICMSTC_ADR_SEQS();
        for (int i=0;i<5;i++) CNT_SEQS[i] = new CICMSTC_CNT_SEQS();
    }
}
