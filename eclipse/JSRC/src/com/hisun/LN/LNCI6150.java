package com.hisun.LN;

public class LNCI6150 {
    public char FUNC_CD = ' ';
    public String CONT_NO = " ";
    public char FLG = ' ';
    public LNCI6150_DATE_ARY[] DATE_ARY = new LNCI6150_DATE_ARY[5];
    public LNCI6150() {
        for (int i=0;i<5;i++) DATE_ARY[i] = new LNCI6150_DATE_ARY();
    }
}
