package com.hisun.DD;

public class DDCSMTDQ_TIR_DATA {
    public DDCSMTDQ_TIR_INF[] TIR_INF = new DDCSMTDQ_TIR_INF[5];
    public DDCSMTDQ_TIR_DATA() {
        for (int i=0;i<5;i++) TIR_INF[i] = new DDCSMTDQ_TIR_INF();
    }
}
