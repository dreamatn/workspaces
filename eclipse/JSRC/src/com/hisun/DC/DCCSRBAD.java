package com.hisun.DC;

public class DCCSRBAD {
    public String CARD_PROD = " ";
    public String PROD_NAME = " ";
    public DCCSRBAD_E_DATA[] E_DATA = new DCCSRBAD_E_DATA[10];
    public DCCSRBAD_A_DATA[] A_DATA = new DCCSRBAD_A_DATA[10];
    public DCCSRBAD() {
        for (int i=0;i<10;i++) E_DATA[i] = new DCCSRBAD_E_DATA();
        for (int i=0;i<10;i++) A_DATA[i] = new DCCSRBAD_A_DATA();
    }
}
