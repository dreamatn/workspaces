package com.hisun.BP;

public class BPCQTHOL {
    public BPCQTHOL_RC RC = new BPCQTHOL_RC();
    public BPCQTHOL_INPUT_DAT INPUT_DAT = new BPCQTHOL_INPUT_DAT();
    public BPCQTHOL_HOL_DATA[] HOL_DATA = new BPCQTHOL_HOL_DATA[50];
    public BPCQTHOL() {
        for (int i=0;i<50;i++) HOL_DATA[i] = new BPCQTHOL_HOL_DATA();
    }
}
