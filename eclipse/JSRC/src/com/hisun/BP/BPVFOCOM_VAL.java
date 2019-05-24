package com.hisun.BP;

public class BPVFOCOM_VAL {
    public String REB_CODE = " ";
    public double UP_AMT = 0;
    public double UP_PER = 0;
    public double LOW_AMT = 0;
    public double LOW_PER = 0;
    public String CHG_CPNT = " ";
    public BPVFOCOM_FAV_DATA[] FAV_DATA = new BPVFOCOM_FAV_DATA[9];
    public char FAV_SELECT = ' ';
    public String FAV_CPNT = " ";
    public BPVFOCOM_VAL() {
        for (int i=0;i<9;i++) FAV_DATA[i] = new BPVFOCOM_FAV_DATA();
    }
}
