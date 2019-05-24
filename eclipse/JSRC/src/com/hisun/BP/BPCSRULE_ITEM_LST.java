package com.hisun.BP;

public class BPCSRULE_ITEM_LST {
    public BPCSRULE_ITEM_LST_DATA[] ITEM_LST_DATA = new BPCSRULE_ITEM_LST_DATA[5];
    public BPCSRULE_ITEM_LST() {
        for (int i=0;i<5;i++) ITEM_LST_DATA[i] = new BPCSRULE_ITEM_LST_DATA();
    }
}
