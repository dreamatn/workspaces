package com.hisun.BP;

public class BPCSMCAL_CAL_DETAIL {
    public BPCSMCAL_MONTH[] MONTH = new BPCSMCAL_MONTH[12];
    public int LST_DATE = 0;
    public String LST_TLT = " ";
    public BPCSMCAL_CAL_DETAIL() {
        for (int i=0;i<12;i++) MONTH[i] = new BPCSMCAL_MONTH();
    }
}
