package com.hisun.LN;

public class LNCI7300 {
    public char FUNC_CD = ' ';
    public String CONT_NO = " ";
    public char FLG = ' ';
    public LNCI7300_DATE_ARY[] DATE_ARY = new LNCI7300_DATE_ARY[12];
    public LNCI7300() {
        for (int i=0;i<12;i++) DATE_ARY[i] = new LNCI7300_DATE_ARY();
    }
}
