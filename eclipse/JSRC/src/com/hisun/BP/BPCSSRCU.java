package com.hisun.BP;

public class BPCSSRCU {
    public int BR = 0;
    public String BASE_TYP = " ";
    public String NAME = " ";
    public int DT = 0;
    public BPCSSRCU_TENOR_A[] TENOR_A = new BPCSSRCU_TENOR_A[20];
    public BPCSSRCU_TBL_A[] TBL_A = new BPCSSRCU_TBL_A[20];
    public BPCSSRCU_REF_A[] REF_A = new BPCSSRCU_REF_A[20];
    public BPCSSRCU() {
        for (int i=0;i<20;i++) TENOR_A[i] = new BPCSSRCU_TENOR_A();
        for (int i=0;i<20;i++) TBL_A[i] = new BPCSSRCU_TBL_A();
        for (int i=0;i<20;i++) REF_A[i] = new BPCSSRCU_REF_A();
    }
}
