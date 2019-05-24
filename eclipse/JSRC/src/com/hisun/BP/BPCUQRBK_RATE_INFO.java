package com.hisun.BP;

public class BPCUQRBK_RATE_INFO {
    public int BR = 0;
    public String CCY = " ";
    public String BASE_TYPE = " ";
    public String TENOR = " ";
    public String RATE_ID = " ";
    public BPCUQRBK_RATE_DETAIL[] RATE_DETAIL = new BPCUQRBK_RATE_DETAIL[366];
    public BPCUQRBK_RATE_INFO() {
        for (int i=0;i<366;i++) RATE_DETAIL[i] = new BPCUQRBK_RATE_DETAIL();
    }
}
