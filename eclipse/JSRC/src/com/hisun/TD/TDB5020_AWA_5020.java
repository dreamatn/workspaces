package com.hisun.TD;

public class TDB5020_AWA_5020 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String RUL_CD = " ";
    public short RUL_CD_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String CDESC = " ";
    public short CDESC_NO = 0;
    public String DESC = " ";
    public short DESC_NO = 0;
    public int SDT = 0;
    public short SDT_NO = 0;
    public int DDT = 0;
    public short DDT_NO = 0;
    public TDB5020_OC_RACD[] OC_RACD = new TDB5020_OC_RACD[30];
    public String SMK = " ";
    public short SMK_NO = 0;
    public TDB5020_AWA_5020() {
        for (int i=0;i<30;i++) OC_RACD[i] = new TDB5020_OC_RACD();
    }
}
