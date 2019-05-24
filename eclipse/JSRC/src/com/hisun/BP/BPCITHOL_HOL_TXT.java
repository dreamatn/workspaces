package com.hisun.BP;

public class BPCITHOL_HOL_TXT {
    public int HOL_EFF_DT = 0;
    public BPCITHOL_HOL_DATA[] HOL_DATA = new BPCITHOL_HOL_DATA[50];
    public BPCITHOL_HOL_TXT() {
        for (int i=0;i<50;i++) HOL_DATA[i] = new BPCITHOL_HOL_DATA();
    }
}
