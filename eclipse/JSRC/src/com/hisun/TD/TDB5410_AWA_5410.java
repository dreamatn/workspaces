package com.hisun.TD;

public class TDB5410_AWA_5410 {
    public char FUNC2 = ' ';
    public short FUNC2_NO = 0;
    public String DOCU_NO = " ";
    public short DOCU_NO_NO = 0;
    public TDB5410_DOCUTERM[] DOCUTERM = new TDB5410_DOCUTERM[10];
    public TDB5410_AWA_5410() {
        for (int i=0;i<10;i++) DOCUTERM[i] = new TDB5410_DOCUTERM();
    }
}
