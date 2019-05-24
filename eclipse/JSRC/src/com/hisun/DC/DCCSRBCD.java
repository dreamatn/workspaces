package com.hisun.DC;

public class DCCSRBCD {
    public String CARD_PROD_CD = " ";
    public DCCSRBCD_RBCD_DATA[] RBCD_DATA = new DCCSRBCD_RBCD_DATA[10];
    public DCCSRBCD() {
        for (int i=0;i<10;i++) RBCD_DATA[i] = new DCCSRBCD_RBCD_DATA();
    }
}
