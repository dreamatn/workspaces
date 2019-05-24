package com.hisun.BP;

public class BPCSCFDR {
    public int MOVE_DT = 0;
    public int CONF_NO = 0;
    public String BV_CODE = " ";
    public int BV_DATE = 0;
    public String BV_NO = " ";
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public char CS_KIND = ' ';
    public BPCSCFDR_DATA_INFO[] DATA_INFO = new BPCSCFDR_DATA_INFO[5];
    public int APP_NO = 0;
    public BPCSCFDR() {
        for (int i=0;i<5;i++) DATA_INFO[i] = new BPCSCFDR_DATA_INFO();
    }
}
