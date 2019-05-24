package com.hisun.BP;

public class BPCSBVRG {
    public BPCSBVRG_RC RC = new BPCSBVRG_RC();
    public char FUNC = ' ';
    public String OUTPUT_FMT = " ";
    public String BV_CODE = " ";
    public BPCSBVRG_BR_INFO[] BR_INFO = new BPCSBVRG_BR_INFO[30];
    public String BV_CNM = " ";
    public String BV_ENM = " ";
    public int UPD_DATE = 0;
    public String UPD_TLR = " ";
    public int CRT_DATE = 0;
    public String CRT_TLR = " ";
    public BPCSBVRG() {
        for (int i=0;i<30;i++) BR_INFO[i] = new BPCSBVRG_BR_INFO();
    }
}
