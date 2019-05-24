package com.hisun.CI;

public class CICCHDC {
    public CICCHDC_RC RC = new CICCHDC_RC();
    public char FUNC = ' ';
    public CICCHDC_DATA_FOR_CHANGE DATA_FOR_CHANGE = new CICCHDC_DATA_FOR_CHANGE();
    public CICCHDC_AGT_NO_AREA[] AGT_NO_AREA = new CICCHDC_AGT_NO_AREA[50];
    public CICCHDC() {
        for (int i=0;i<50;i++) AGT_NO_AREA[i] = new CICCHDC_AGT_NO_AREA();
    }
}
