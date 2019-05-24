package com.hisun.TD;

public class TDAWA {
    public char FUNC = ' ';
    public String RUL_CD = " ";
    public String CCY = " ";
    public String CDESC = " ";
    public String DESC = " ";
    public int SDT = 0;
    public int DDT = 0;
    public TDC5020_OC_RACD[] OC_RACD = new TDC5020_OC_RACD[30];
    public String SMK = " ";
    public TDAWA() {
        for (int i=0;i<30;i++) OC_RACD[i] = new TDC5020_OC_RACD();
    }
}
