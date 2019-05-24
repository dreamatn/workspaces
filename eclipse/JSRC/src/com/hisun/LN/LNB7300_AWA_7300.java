package com.hisun.LN;

public class LNB7300_AWA_7300 {
    public char FUNC_CD = ' ';
    public short FUNC_CD_NO = 0;
    public String CONT_NO = " ";
    public short CONT_NO_NO = 0;
    public char FLG = ' ';
    public short FLG_NO = 0;
    public LNB7300_DATE_ARY[] DATE_ARY = new LNB7300_DATE_ARY[12];
    public LNB7300_AWA_7300() {
        for (int i=0;i<12;i++) DATE_ARY[i] = new LNB7300_DATE_ARY();
    }
}
