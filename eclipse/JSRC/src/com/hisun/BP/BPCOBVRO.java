package com.hisun.BP;

public class BPCOBVRO {
    public char FUNC = ' ';
    public String BV_CODE = " ";
    public BPCOBVRO_BR_INFO[] BR_INFO = new BPCOBVRO_BR_INFO[30];
    public String BV_CNM = " ";
    public char FILLER6 = 0X02;
    public String BV_ENM = " ";
    public int UPD_DATE = 0;
    public String UPD_TLR = " ";
    public int CRT_DATE = 0;
    public String CRT_TLR = " ";
    public BPCOBVRO() {
        for (int i=0;i<30;i++) BR_INFO[i] = new BPCOBVRO_BR_INFO();
    }
}
