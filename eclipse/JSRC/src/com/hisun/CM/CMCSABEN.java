package com.hisun.CM;

public class CMCSABEN {
    public String CTL = " ";
    public double TXN_AMT = 0;
    public CMCSABEN_TXN_INF[] TXN_INF = new CMCSABEN_TXN_INF[20];
    public CMCSABEN() {
        for (int i=0;i<20;i++) TXN_INF[i] = new CMCSABEN_TXN_INF();
    }
}
