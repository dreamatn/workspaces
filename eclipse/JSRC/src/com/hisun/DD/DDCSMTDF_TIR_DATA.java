package com.hisun.DD;

public class DDCSMTDF_TIR_DATA {
    public DDCSMTDF_TIR_INF[] TIR_INF = new DDCSMTDF_TIR_INF[5];
    public DDCSMTDF_TIR_DATA() {
        for (int i=0;i<5;i++) TIR_INF[i] = new DDCSMTDF_TIR_INF();
    }
}
