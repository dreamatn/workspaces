package com.hisun.BP;

public class BPCSSCMO {
    public BPCSSCMO_RC RC = new BPCSSCMO_RC();
    public char FLG = ' ';
    public int BR = 0;
    public int BR_CHG = 0;
    public String PL_BOX_NO = " ";
    public int CRT_DATE = 0;
    public String TLR = " ";
    public String TLR_CHG = " ";
    public int TR_DATE = 0;
    public String DRW_NM = " ";
    public String ID_TYP = " ";
    public String ID_NO = " ";
    public long CONF_NO = 0;
    public int MOV_DT = 0;
    public BPCSSCMO_DATA_INFO[] DATA_INFO = new BPCSSCMO_DATA_INFO[10];
    public BPCSSCMO() {
        for (int i=0;i<10;i++) DATA_INFO[i] = new BPCSSCMO_DATA_INFO();
    }
}
