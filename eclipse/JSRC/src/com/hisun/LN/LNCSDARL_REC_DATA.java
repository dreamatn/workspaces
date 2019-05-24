package com.hisun.LN;

public class LNCSDARL_REC_DATA {
    public String CONTRACT_NO = " ";
    public char WHD_RUL = ' ';
    public LNCSDARL_AC_DATA[] AC_DATA = new LNCSDARL_AC_DATA[5];
    public LNCSDARL_REC_DATA() {
        for (int i=0;i<5;i++) AC_DATA[i] = new LNCSDARL_AC_DATA();
    }
}
