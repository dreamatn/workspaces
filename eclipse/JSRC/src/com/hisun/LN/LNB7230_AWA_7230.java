package com.hisun.LN;

public class LNB7230_AWA_7230 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String CONT_NO = " ";
    public short CONT_NO_NO = 0;
    public char WHD_RUL = ' ';
    public short WHD_RUL_NO = 0;
    public LNB7230_AC_DATA[] AC_DATA = new LNB7230_AC_DATA[5];
    public int AC_CNT = 0;
    public short AC_CNT_NO = 0;
    public LNB7230_AWA_7230() {
        for (int i=0;i<5;i++) AC_DATA[i] = new LNB7230_AC_DATA();
    }
}
