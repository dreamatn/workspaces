package com.hisun.BP;

public class BPCOSUOD_VAL {
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public BPCOSUOD_UOD_AMT[] UOD_AMT = new BPCOSUOD_UOD_AMT[10];
    public BPCOSUOD_VAL() {
        for (int i=0;i<10;i++) UOD_AMT[i] = new BPCOSUOD_UOD_AMT();
    }
}
