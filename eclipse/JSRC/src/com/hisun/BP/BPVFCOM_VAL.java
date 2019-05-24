package com.hisun.BP;

public class BPVFCOM_VAL {
    public BPVFCOM_FAV_DATA[] FAV_DATA = new BPVFCOM_FAV_DATA[9];
    public char FAV_SELECT = ' ';
    public BPVFCOM_VAL() {
        for (int i=0;i<9;i++) FAV_DATA[i] = new BPVFCOM_FAV_DATA();
    }
}
