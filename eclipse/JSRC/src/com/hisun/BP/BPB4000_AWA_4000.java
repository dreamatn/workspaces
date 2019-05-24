package com.hisun.BP;

public class BPB4000_AWA_4000 {
    public char FUNC_CD = ' ';
    public short FUNC_CD_NO = 0;
    public long ACCT_CD = 0;
    public short ACCT_CD_NO = 0;
    public long ITEM_NO = 0;
    public short ITEM_NO_NO = 0;
    public String ACCT_NM = " ";
    public short ACCT_NM_NO = 0;
    public String ITEM_NM = " ";
    public short ITEM_NM_NO = 0;
    public char ACCT_OFF = ' ';
    public short ACCT_OFF_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public String RMK = " ";
    public short RMK_NO = 0;
    public String AP_MMO = " ";
    public short AP_MMO_NO = 0;
    public String TR_CD = " ";
    public short TR_CD_NO = 0;
    public String CASE_NO = " ";
    public short CASE_NO_NO = 0;
    public String DESC = " ";
    public short DESC_NO = 0;
    public char SORT_IND = ' ';
    public short SORT_IND_NO = 0;
    public short CNT = 0;
    public short CNT_NO = 0;
    public BPB4000_SEQ[] SEQ = new BPB4000_SEQ[99];
    public BPB4000_AWA_4000() {
        for (int i=0;i<99;i++) SEQ[i] = new BPB4000_SEQ();
    }
}
