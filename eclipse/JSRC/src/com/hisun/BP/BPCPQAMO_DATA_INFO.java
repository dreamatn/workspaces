package com.hisun.BP;

public class BPCPQAMO_DATA_INFO {
    public String CNTR_TYPE = " ";
    public String PROD_TYPE = " ";
    public int BR = 0;
    public String MOD_NO = " ";
    public String MOD_NAME = " ";
    public BPCPQAMO_EVENT[] EVENT = new BPCPQAMO_EVENT[60];
    public String UPD_TEL = " ";
    public int UPD_DATE = 0;
    public int UPD_TIME = 0;
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCPQAMO_DATA_INFO() {
        for (int i=0;i<60;i++) EVENT[i] = new BPCPQAMO_EVENT();
    }
}
