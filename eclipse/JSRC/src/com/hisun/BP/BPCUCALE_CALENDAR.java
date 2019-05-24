package com.hisun.BP;

public class BPCUCALE_CALENDAR {
    public BPCUCALE_MONTH[] MONTH = new BPCUCALE_MONTH[12];
    public BPCUCALE_CALENDAR() {
        for (int i=0;i<12;i++) MONTH[i] = new BPCUCALE_MONTH();
    }
}
