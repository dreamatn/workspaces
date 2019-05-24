package com.hisun.BP;

public class BPCOINVO {
    public int DATE = 0;
    public long JRNNO = 0;
    public int BR = 0;
    public String INVNTY1 = " ";
    public String INVNTY2 = " ";
    public String HANDLER1 = " ";
    public String HANDLER2 = " ";
    public BPCOINVO_BV_INFO[] BV_INFO = new BPCOINVO_BV_INFO[200];
    public BPCOINVO() {
        for (int i=0;i<200;i++) BV_INFO[i] = new BPCOINVO_BV_INFO();
    }
}
