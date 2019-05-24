package com.hisun.BP;

public class BPCTHOL {
    public BPCTHOL_KEY KEY = new BPCTHOL_KEY();
    public int CNT = 0;
    public BPCTHOL_HOL_DATA[] HOL_DATA = new BPCTHOL_HOL_DATA[50];
    public int LAST_DATE = 0;
    public String LAST_TELLER = " ";
    public BPCTHOL() {
        for (int i=0;i<50;i++) HOL_DATA[i] = new BPCTHOL_HOL_DATA();
    }
}
