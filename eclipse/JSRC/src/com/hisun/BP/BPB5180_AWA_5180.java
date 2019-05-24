package com.hisun.BP;

public class BPB5180_AWA_5180 {
    public String EXR_TYPE = " ";
    public short EXR_TYPE_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EFF_TIME = 0;
    public short EFF_TIME_NO = 0;
    public String FWD_TENR = " ";
    public short FWD_TENR_NO = 0;
    public String BASE_TYP = " ";
    public short BASE_TYP_NO = 0;
    public String BASE_CCY = " ";
    public short BASE_CCY_NO = 0;
    public BPB5180_RATE_INF[] RATE_INF = new BPB5180_RATE_INF[20];
    public char CONT_FLG = ' ';
    public short CONT_FLG_NO = 0;
    public short CMPL_CNT = 0;
    public short CMPL_CNT_NO = 0;
    public short REC_CNT = 0;
    public short REC_CNT_NO = 0;
    public char CMPL_FLG = ' ';
    public short CMPL_FLG_NO = 0;
    public BPB5180_AWA_5180() {
        for (int i=0;i<20;i++) RATE_INF[i] = new BPB5180_RATE_INF();
    }
}
