package com.hisun.BP;

public class BPCBCHDY_HOL_TXT {
    public int HOL_EFF_DT = 0;
    public BPCBCHDY_HOL_DATA[] HOL_DATA = new BPCBCHDY_HOL_DATA[50];
    public BPCBCHDY_HOL_TXT() {
        for (int i=0;i<50;i++) HOL_DATA[i] = new BPCBCHDY_HOL_DATA();
    }
}
