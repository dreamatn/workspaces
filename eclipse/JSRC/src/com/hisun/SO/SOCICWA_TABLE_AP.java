package com.hisun.SO;

public class SOCICWA_TABLE_AP {
    public SOCICWA_AP_ITEM[] AP_ITEM = new SOCICWA_AP_ITEM[64];
    public SOCICWA_TABLE_AP() {
        for (int i=0;i<64;i++) AP_ITEM[i] = new SOCICWA_AP_ITEM();
    }
}
