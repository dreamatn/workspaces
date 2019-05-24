package com.hisun.BP;

public class BPCSSCTD {
    public char FUNC = ' ';
    public int BR = 0;
    public int BR_CHG = 0;
    public String PL_BOX_NO = " ";
    public int CRT_DATE = 0;
    public String TLR = " ";
    public int TR_DATE = 0;
    public String DRW_NM = " ";
    public String ID_TYP = " ";
    public String ID_NO = " ";
    public long CONF_NO = 0;
    public int MOVE_DT = 0;
    public BPCSSCTD_DATA_INFO[] DATA_INFO = new BPCSSCTD_DATA_INFO[10];
    public BPCSSCTD() {
        for (int i=0;i<10;i++) DATA_INFO[i] = new BPCSSCTD_DATA_INFO();
    }
}
