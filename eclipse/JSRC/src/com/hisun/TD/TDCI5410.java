package com.hisun.TD;

public class TDCI5410 {
    public char FUNC2 = ' ';
    public String DOCU_NO = " ";
    public TDCI5410_DOCUTERM[] DOCUTERM = new TDCI5410_DOCUTERM[10];
    public TDCI5410() {
        for (int i=0;i<10;i++) DOCUTERM[i] = new TDCI5410_DOCUTERM();
    }
}
