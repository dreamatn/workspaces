package com.hisun.BP;

public class BPCOFCOM_VAL {
    public BPCOFCOM_FAV_DATA[] FAV_DATA = new BPCOFCOM_FAV_DATA[9];
    public char FAV_SELECT = ' ';
    public BPCOFCOM_VAL() {
        for (int i=0;i<9;i++) FAV_DATA[i] = new BPCOFCOM_FAV_DATA();
    }
}
