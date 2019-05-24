package com.hisun.LN;

public class LNCIHDCK_INPUT_INFO {
    public char WARN_AUTH = ' ';
    public char RTN_WORKDAY = ' ';
    public String CONTRACT_NO = " ";
    public String TRACOMMT_NO = " ";
    public int INPUT_DATE = 0;
    public char HOLIDAY_MTH = ' ';
    public char HOLIDAY_ORDE = ' ';
    public String CONTRACT_CCY = " ";
    public LNCIHDCK_CNTY[] CNTY = new LNCIHDCK_CNTY[5];
    public int BR = 0;
    public LNCIHDCK_INPUT_INFO() {
        for (int i=0;i<5;i++) CNTY[i] = new LNCIHDCK_CNTY();
    }
}
