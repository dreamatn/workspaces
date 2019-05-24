package com.hisun.LN;

public class LNCX705 {
    public char FUNC = ' ';
    public String TYPE = " ";
    public String CODE = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public char FILLER6 = 0X02;
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public short VAL_LEN = 0;
    public char FILLER10 = 0X01;
    public char VLDT_FLG = ' ';
    public LNCX705_FROM_CNTR[] FROM_CNTR = new LNCX705_FROM_CNTR[10];
    public char RATE_TYP = ' ';
    public String INT_FML = " ";
    public short TO_NO = 0;
    public LNCX705() {
        for (int i=0;i<10;i++) FROM_CNTR[i] = new LNCX705_FROM_CNTR();
    }
}
