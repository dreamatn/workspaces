package com.hisun.BP;

public class BPCOFAGR {
    public char RETURN_INFOR = ' ';
    public char FUNC = ' ';
    public char OPT = ' ';
    public String OUTPUT_FMT = " ";
    public BPCOFAGR_KEY KEY = new BPCOFAGR_KEY();
    public BPCOFAGR_DATE DATE = new BPCOFAGR_DATE();
    public BPCOFAGR_VAL VAL = new BPCOFAGR_VAL();
    public BPCOFAGR_INFOR[] INFOR = new BPCOFAGR_INFOR[50];
    public BPCOFAGR() {
        for (int i=0;i<50;i++) INFOR[i] = new BPCOFAGR_INFOR();
    }
}
