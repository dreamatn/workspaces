package com.hisun.BP;

public class BPCOUODO_VAL {
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public BPCOUODO_UOD_AMT[] UOD_AMT = new BPCOUODO_UOD_AMT[10];
    public BPCOUODO_VAL() {
        for (int i=0;i<10;i++) UOD_AMT[i] = new BPCOUODO_UOD_AMT();
    }
}
