package com.hisun.BP;

public class BPCOINTU {
    public int DT = 0;
    public int BR = 0;
    public int CNT = 0;
    public BPCOINTU_INTR_TBL[] INTR_TBL = new BPCOINTU_INTR_TBL[10];
    public BPCOINTU() {
        for (int i=0;i<10;i++) INTR_TBL[i] = new BPCOINTU_INTR_TBL();
    }
}
