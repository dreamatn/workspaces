package com.hisun.BP;

public class BPCSHITI {
    public int BR = 0;
    public int STARTD = 0;
    public int ENDD = 0;
    public BPCSHITI_INTR_TBL[] INTR_TBL = new BPCSHITI_INTR_TBL[10];
    public BPCSHITI() {
        for (int i=0;i<10;i++) INTR_TBL[i] = new BPCSHITI_INTR_TBL();
    }
}
