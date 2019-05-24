package com.hisun.BP;

public class BPCSCALE_CALENDAR {
    public BPCSCALE_MONTH[] MONTH = new BPCSCALE_MONTH[12];
    public BPCSCALE_CALENDAR() {
        for (int i=0;i<12;i++) MONTH[i] = new BPCSCALE_MONTH();
    }
}
