package com.hisun.BP;

public class BPCSSRCU_RATE_A {
    public BPCSSRCU_RATE_TBL_A[] RATE_TBL_A = new BPCSSRCU_RATE_TBL_A[20];
    public BPCSSRCU_RATE_A() {
        for (int i=0;i<20;i++) RATE_TBL_A[i] = new BPCSSRCU_RATE_TBL_A();
    }
}
