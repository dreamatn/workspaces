package com.hisun.BP;

public class BPCSSCBB {
    public char FUNC = ' ';
    public int BR = 0;
    public String PL_BOX_NO = " ";
    public String PL_BOX_CHG = " ";
    public int CRT_DATE = 0;
    public String TLR = " ";
    public String TLR_CHG = " ";
    public int TR_DATE = 0;
    public String DRW_NM = " ";
    public String ID_TYP = " ";
    public String ID_NO = " ";
    public BPCSSCBB_DATA_INFO[] DATA_INFO = new BPCSSCBB_DATA_INFO[10];
    public char TXTYP = ' ';
    public BPCSSCBB() {
        for (int i=0;i<10;i++) DATA_INFO[i] = new BPCSSCBB_DATA_INFO();
    }
}
