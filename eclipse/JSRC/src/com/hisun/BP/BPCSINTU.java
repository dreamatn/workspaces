package com.hisun.BP;

public class BPCSINTU {
    public int BR = 0;
    public BPCSINTU_INTR_TBL[] INTR_TBL = new BPCSINTU_INTR_TBL[10];
    public BPCSINTU() {
        for (int i=0;i<10;i++) INTR_TBL[i] = new BPCSINTU_INTR_TBL();
    }
}
