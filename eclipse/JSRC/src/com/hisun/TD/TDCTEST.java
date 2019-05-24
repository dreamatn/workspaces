package com.hisun.TD;

public class TDCTEST {
    public char FUNC = ' ';
    public String RUL_CD = " ";
    public String CCY = " ";
    public String CDESC = " ";
    public String DESC = " ";
    public int SDT = 0;
    public int DDT = 0;
    public TDCTEST_OC_RACD[] OC_RACD = new TDCTEST_OC_RACD[31];
    public String SMK = " ";
    public TDCTEST() {
        for (int i=0;i<31;i++) OC_RACD[i] = new TDCTEST_OC_RACD();
    }
}
