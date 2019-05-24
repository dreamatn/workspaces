package com.hisun.LN;

public class LNCX706 {
    public String TYPE = " ";
    public String CODE = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public char FILLER5 = 0X02;
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public short VAL_LEN = 0;
    public char FILLER9 = 0X01;
    public char REPY_MTH = ' ';
    public LNCX706_DATA[] DATA = new LNCX706_DATA[5];
    public LNCX706() {
        for (int i=0;i<5;i++) DATA[i] = new LNCX706_DATA();
    }
}
