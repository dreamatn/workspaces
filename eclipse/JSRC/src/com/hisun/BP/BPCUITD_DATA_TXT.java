package com.hisun.BP;

public class BPCUITD_DATA_TXT {
    public BPCUITD_REL_ITMS[] REL_ITMS = new BPCUITD_REL_ITMS[32];
    public String UPD_TLR = " ";
    public int UPD_DATE = 0;
    public int UPD_TIME = 0;
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCUITD_DATA_TXT() {
        for (int i=0;i<32;i++) REL_ITMS[i] = new BPCUITD_REL_ITMS();
    }
}
