package com.hisun.TD;

public class TDCDOCU {
    public String DOCU_NO = " ";
    public TDCDOCU_DOCUTERM[] DOCUTERM = new TDCDOCU_DOCUTERM[10];
    public TDCDOCU() {
        for (int i=0;i<10;i++) DOCUTERM[i] = new TDCDOCU_DOCUTERM();
    }
}
