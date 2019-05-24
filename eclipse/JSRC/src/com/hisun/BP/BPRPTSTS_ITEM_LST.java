package com.hisun.BP;

public class BPRPTSTS_ITEM_LST {
    public BPRPTSTS_ITEM_LST_DATA[] ITEM_LST_DATA = new BPRPTSTS_ITEM_LST_DATA[100];
    public BPRPTSTS_ITEM_LST() {
        for (int i=0;i<100;i++) ITEM_LST_DATA[i] = new BPRPTSTS_ITEM_LST_DATA();
    }
}
