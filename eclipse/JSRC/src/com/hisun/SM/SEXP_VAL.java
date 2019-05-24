package com.hisun.SM;

public class SEXP_VAL {
    String DER_DESC = " ";
    int EFF_DATE = 0;
    int EXP_DATE = 0;
    SEXP_EXP_DATA[] EXP_DATA = new SEXP_EXP_DATA[50];
    int LAST_DATE = 0;
    String LAST_TELL = " ";
    String SUP_TEL1 = " ";
    String SUP_TEL2 = " ";
    public SEXP_VAL() {
        for (int i=0;i<50;i++) EXP_DATA[i] = new SEXP_EXP_DATA();
    }
}
