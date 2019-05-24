package com.hisun.BP;

public class BPB1138_AWA_1138 {
    public String REB_CODE = " ";
    public short REB_CODE_NO = 0;
    public String REB_DESC = " ";
    public short REB_DESC_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public String TX_MMO = " ";
    public short TX_MMO_NO = 0;
    public char R_CYCLE = ' ';
    public short R_CYCLE_NO = 0;
    public short PER_CNT = 0;
    public short PER_CNT_NO = 0;
    public short REB_STDT = 0;
    public short REB_STDT_NO = 0;
    public short REB_DATE = 0;
    public short REB_DATE_NO = 0;
    public int L_REB_DT = 0;
    public short L_REB_DT_NO = 0;
    public int N_REB_DT = 0;
    public short N_REB_DT_NO = 0;
    public char REB_TYPE = ' ';
    public short REB_TYPE_NO = 0;
    public char AGG_TYPE = ' ';
    public short AGG_TYPE_NO = 0;
    public BPB1138_REB_INFO[] REB_INFO = new BPB1138_REB_INFO[5];
    public BPB1138_AWA_1138() {
        for (int i=0;i<5;i++) REB_INFO[i] = new BPB1138_REB_INFO();
    }
}
