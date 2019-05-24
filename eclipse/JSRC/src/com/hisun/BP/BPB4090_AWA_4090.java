package com.hisun.BP;

public class BPB4090_AWA_4090 {
    public char FUNC_1 = ' ';
    public short FUNC_1_NO = 0;
    public String MOD_NO = " ";
    public short MOD_NO_NO = 0;
    public String BOOK_FLG = " ";
    public short BOOK_FLG_NO = 0;
    public short CNT = 0;
    public short CNT_NO = 0;
    public String ITM_PNT = " ";
    public short ITM_PNT_NO = 0;
    public String ITM_NO = " ";
    public short ITM_NO_NO = 0;
    public BPB4090_DATA_FLG[] DATA_FLG = new BPB4090_DATA_FLG[20];
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public int EFF_DT = 0;
    public short EFF_DT_NO = 0;
    public int EXP_DT = 0;
    public short EXP_DT_NO = 0;
    public String CNTR_TYP = " ";
    public short CNTR_TYP_NO = 0;
    public String PROD_TYP = " ";
    public short PROD_TYP_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public String AM_NAME = " ";
    public short AM_NAME_NO = 0;
    public int SEQ = 0;
    public short SEQ_NO = 0;
    public char RUN_TYP = ' ';
    public short RUN_TYP_NO = 0;
    public BPB4090_AWA_4090() {
        for (int i=0;i<20;i++) DATA_FLG[i] = new BPB4090_DATA_FLG();
    }
}
