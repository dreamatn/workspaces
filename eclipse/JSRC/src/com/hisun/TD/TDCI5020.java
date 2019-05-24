package com.hisun.TD;

public class TDCI5020 {
    public char FUNC = ' ';
    public String RUL_CD = " ";
    public String CCY = " ";
    public String CDESC = " ";
    public String DESC = " ";
    public int SDT = 0;
    public int DDT = 0;
    public TDCI5020_OC_RACD[] OC_RACD = new TDCI5020_OC_RACD[30];
    public String SMK = " ";
    public TDCI5020() {
        for (int i=0;i<30;i++) OC_RACD[i] = new TDCI5020_OC_RACD();
    }
}
