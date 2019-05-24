package com.hisun.BP;

public class BPCOSRCI {
    public int BR = 0;
    public String CCY = " ";
    public String BASE_TYP = " ";
    public String NAME = " ";
    public char NAME_FILLER = ' ';
    public BPCOSRCI_TBL_TENOR[] TBL_TENOR = new BPCOSRCI_TBL_TENOR[20];
    public BPCOSRCI_TBL TBL = new BPCOSRCI_TBL();
    public BPCOSRCI_TBL_DT[] TBL_DT = new BPCOSRCI_TBL_DT[20];
    public BPCOSRCI_REF_A[] REF_A = new BPCOSRCI_REF_A[20];
    public BPCOSRCI() {
        for (int i=0;i<20;i++) TBL_TENOR[i] = new BPCOSRCI_TBL_TENOR();
        for (int i=0;i<20;i++) TBL_DT[i] = new BPCOSRCI_TBL_DT();
        for (int i=0;i<20;i++) REF_A[i] = new BPCOSRCI_REF_A();
    }
}
