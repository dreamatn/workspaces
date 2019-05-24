package com.hisun.GD;

public class GDCOEQTR {
    public GDCOEQTR_PAGE_INF PAGE_INF = new GDCOEQTR_PAGE_INF();
    public GDCOEQTR_OUTPUT_LST[] OUTPUT_LST = new GDCOEQTR_OUTPUT_LST[25];
    public GDCOEQTR() {
        for (int i=0;i<25;i++) OUTPUT_LST[i] = new GDCOEQTR_OUTPUT_LST();
    }
}
