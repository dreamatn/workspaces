package com.hisun.LN;

public class LNB6150_AWA_6150 {
    public char FUNC_CD = ' ';
    public short FUNC_CD_NO = 0;
    public String CONT_NO = " ";
    public short CONT_NO_NO = 0;
    public char FLG = ' ';
    public short FLG_NO = 0;
    public LNB6150_DATE_ARY[] DATE_ARY = new LNB6150_DATE_ARY[5];
    public LNB6150_AWA_6150() {
        for (int i=0;i<5;i++) DATE_ARY[i] = new LNB6150_DATE_ARY();
    }
}
