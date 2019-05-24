package com.hisun.BP;

public class BPCOSRCU {
    public int DT = 0;
    public int BR = 0;
    public String BASE_TYP = " ";
    public String NAME = " ";
    public char NAME_FILLER = ' ';
    public BPCOSRCU_TBL_TENOR[] TBL_TENOR = new BPCOSRCU_TBL_TENOR[20];
    public BPCOSRCU_TBL_LINE[] TBL_LINE = new BPCOSRCU_TBL_LINE[10];
    public int CNT = 0;
    public BPCOSRCU_TBL TBL = new BPCOSRCU_TBL();
    public BPCOSRCU_REF_A[] REF_A = new BPCOSRCU_REF_A[20];
    public BPCOSRCU() {
        for (int i=0;i<20;i++) TBL_TENOR[i] = new BPCOSRCU_TBL_TENOR();
        for (int i=0;i<10;i++) TBL_LINE[i] = new BPCOSRCU_TBL_LINE();
        for (int i=0;i<20;i++) REF_A[i] = new BPCOSRCU_REF_A();
    }
}
