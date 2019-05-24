package com.hisun.BP;

public class BPCSVBLT {
    public BPCSVBLT_RC RC = new BPCSVBLT_RC();
    public int BR = 0;
    public String TLR = " ";
    public String HEAD_NO = " ";
    public String BV_CODE = " ";
    public int NUM = 0;
    public int O_BR = 0;
    public String O_TLR = " ";
    public int O_NO_CNT = 0;
    public BPCSVBLT_O_BV_DATA[] O_BV_DATA = new BPCSVBLT_O_BV_DATA[20];
    public int O_TOTAL_NUM = 0;
    public BPCSVBLT() {
        for (int i=0;i<20;i++) O_BV_DATA[i] = new BPCSVBLT_O_BV_DATA();
    }
}
