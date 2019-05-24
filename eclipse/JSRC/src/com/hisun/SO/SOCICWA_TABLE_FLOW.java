package com.hisun.SO;

public class SOCICWA_TABLE_FLOW {
    public SOCICWA_FLOW_ITEM[] FLOW_ITEM = new SOCICWA_FLOW_ITEM[80];
    public SOCICWA_TABLE_FLOW() {
        for (int i=0;i<80;i++) FLOW_ITEM[i] = new SOCICWA_FLOW_ITEM();
    }
}
