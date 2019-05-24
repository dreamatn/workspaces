package com.hisun.BP;

public class BPCPQENT_DATA_INFO {
    public String MODNO = " ";
    public String EVENT_TYPE = " ";
    public String GL_BOOK = " ";
    public short CNT = 0;
    public String UPD_TEL = " ";
    public int UPD_DATE = 0;
    public int UPD_TIME = 0;
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCPQENT_EVENT_DATA[] EVENT_DATA = new BPCPQENT_EVENT_DATA[75];
    public BPCPQENT_DATA_INFO() {
        for (int i=0;i<75;i++) EVENT_DATA[i] = new BPCPQENT_EVENT_DATA();
    }
}
